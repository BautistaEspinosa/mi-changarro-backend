package com.baersolutions.mi_changarro_app.modules.surtido.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.CompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.DetalleCompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.Compra;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.DetalleCompra;
import com.baersolutions.mi_changarro_app.modules.surtido.mapper.SurtidoMapper;
import com.baersolutions.mi_changarro_app.modules.surtido.repository.CompraRepository;
import com.baersolutions.mi_changarro_app.modules.surtido.service.CompraService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Surtido.
 *
 * <p>Contiene la lógica de negocio para registrar compras de mercancía,
 * calcular costos unitarios, crear productos cuando no existen, actualizar
 * inventario y consultar compras registradas.
 *
 * @author Baer Solutions
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

  private final CompraRepository compraRepository;

  private final ProductoRepository productoRepository;

  /**
   * Registra una nueva compra de surtido.
   *
   * <p>Por cada producto incluido en la compra, calcula el costo unitario,
   * actualiza el costo vigente del producto, incrementa el stock y actualiza
   * el precio de venta elegido por el usuario.
   *
   * @param dto información de la compra a registrar.
   * @return compra registrada con sus detalles.
   */
  @Override
  @Transactional
  public CompraResponseDTO registrarCompra(final CompraRequestDTO dto) {

    log.info(
        LogMessages.START,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_REGISTRAR_COMPRA
    );

    Compra compra = Compra.builder()
        .montoTotal(BigDecimal.ZERO)
        .build();

    List<DetalleCompra> detallesCompra = dto.detallesCompra()
        .stream()
        .map(detalle -> crearDetalleCompra(compra, detalle))
        .toList();

    BigDecimal montoTotal = detallesCompra.stream()
        .map(DetalleCompra::getCostoTotalProducto)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    compra.setMontoTotal(montoTotal);
    compra.setDetallesCompra(detallesCompra);

    Compra compraGuardada = compraRepository.save(compra);

    log.info(
        LogMessages.SUCCESS,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_REGISTRAR_COMPRA
    );

    return SurtidoMapper.toDTO(compraGuardada);
  }

  /**
   * Obtiene una compra por su identificador.
   *
   * <p>Busca la compra registrada y devuelve la información completa con su
   * cabecera y sus detalles asociados.
   *
   * @param id identificador de la compra.
   * @return compra encontrada.
   */
  @Override
  @Transactional(readOnly = true)
  public CompraResponseDTO obtenerCompraPorId(final Long id) {

    log.info(
        LogMessages.START,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_OBTENER_COMPRA
    );

    Compra compra = compraRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            SurtidoMessages.COMPRA_NO_ENCONTRADA
        ));

    log.info(
        LogMessages.SUCCESS,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_OBTENER_COMPRA
    );

    return SurtidoMapper.toDTO(compra);
  }

  /**
   * Lista el historial de compras registradas.
   *
   * <p>Obtiene todas las compras registradas, ordenadas de la más reciente a
   * la más antigua.
   *
   * @return lista de compras registradas.
   */
  @Override
  @Transactional(readOnly = true)
  public List<CompraResponseDTO> listarCompras() {

    log.info(
        LogMessages.START,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_LISTAR_COMPRAS
    );

    List<CompraResponseDTO> compras = compraRepository.findAllByOrderByCreatedAtDesc()
        .stream()
        .map(SurtidoMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        SurtidoMessages.MODULE,
        SurtidoMessages.OP_LISTAR_COMPRAS
    );

    return compras;
  }

  /**
   * Crea el detalle de compra y actualiza el producto correspondiente.
   *
   * @param compra compra a la que pertenece el detalle.
   * @param detalleDto información del producto comprado.
   * @return detalle de compra generado.
   */
  private DetalleCompra crearDetalleCompra(
      final Compra compra,
      final DetalleCompraRequestDTO detalleDto
  ) {

    Producto producto = obtenerOCrearProducto(detalleDto);

    BigDecimal costoUnitarioCalculado = detalleDto.costoTotalProducto()
        .divide(
            BigDecimal.valueOf(detalleDto.cantidad()),
            BusinessConstants.DECIMAL_SCALE,
            BusinessConstants.DECIMAL_ROUNDING_MODE
        );

    producto.setCostoUnitarioActual(costoUnitarioCalculado);
    producto.setStockActual(producto.getStockActual() + detalleDto.cantidad());
    producto.setPrecioVenta(detalleDto.precioVenta());

    Producto productoGuardado = productoRepository.save(producto);

    return DetalleCompra.builder()
        .compra(compra)
        .producto(productoGuardado)
        .cantidad(detalleDto.cantidad())
        .costoTotalProducto(detalleDto.costoTotalProducto())
        .costoUnitarioCalculado(costoUnitarioCalculado)
        .build();
  }

  /**
   * Obtiene un producto existente por nombre o crea uno nuevo.
   *
   * @param detalleDto información del producto comprado.
   * @return producto existente o nuevo producto construido.
   */
  private Producto obtenerOCrearProducto(final DetalleCompraRequestDTO detalleDto) {

    return productoRepository.findByNombre(detalleDto.nombreProducto())
        .orElseGet(() -> Producto.builder()
            .nombre(detalleDto.nombreProducto())
            .costoUnitarioActual(BigDecimal.ZERO)
            .precioVenta(detalleDto.precioVenta())
            .stockActual(0)
            .stockMinimo(BusinessConstants.STOCK_BAJO_DEFAULT)
            .activo(true)
            .build());
  }

}