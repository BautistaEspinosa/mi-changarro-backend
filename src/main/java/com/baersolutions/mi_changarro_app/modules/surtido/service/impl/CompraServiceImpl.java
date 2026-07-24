package com.baersolutions.mi_changarro_app.modules.surtido.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.inventario.util.ProductoNombreNormalizer;
import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.CompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.DetalleCompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.PrecioSugeridoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.PrecioSugeridoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.Compra;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.DetalleCompra;
import com.baersolutions.mi_changarro_app.modules.surtido.enums.TipoPrecio;
import com.baersolutions.mi_changarro_app.modules.surtido.mapper.SurtidoMapper;
import com.baersolutions.mi_changarro_app.modules.surtido.repository.CompraRepository;
import com.baersolutions.mi_changarro_app.modules.surtido.service.CompraService;
import com.baersolutions.mi_changarro_app.modules.surtido.util.SurtidoCalculator;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Surtido.
 *
 * <p>Contiene la lógica de negocio para registrar compras de mercancía, calcular costos unitarios,
 * determinar precios de venta, crear productos cuando no existen, actualizar inventario y consultar
 * compras registradas.
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
   * Calcula el costo unitario y los precios sugeridos de un producto.
   *
   * <p>Esta operación no persiste información ni modifica el inventario.
   *
   * @param dto información necesaria para realizar el cálculo.
   * @return costo unitario y precios sugeridos.
   */
  @Override
  @Transactional(readOnly = true)
  public PrecioSugeridoResponseDTO calcularPrecios(final PrecioSugeridoRequestDTO dto) {

    log.info(LogMessages.START, SurtidoMessages.MODULE, SurtidoMessages.OP_CALCULAR_PRECIOS);

    BigDecimal costoUnitario =
        SurtidoCalculator.calcularCostoUnitario(dto.costoTotalProducto(), dto.cantidad());

    PrecioSugeridoResponseDTO response =
        new PrecioSugeridoResponseDTO(
            costoUnitario,
            SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_20),
            SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_30),
            SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_50));

    log.info(LogMessages.SUCCESS, SurtidoMessages.MODULE, SurtidoMessages.OP_CALCULAR_PRECIOS);

    return response;
  }

  /**
   * Registra una nueva compra de surtido.
   *
   * <p>Por cada producto incluido en la compra, calcula el costo unitario, determina el precio de
   * venta según la opción seleccionada, actualiza el costo vigente del producto e incrementa su
   * stock.
   *
   * @param dto información de la compra a registrar.
   * @return compra registrada con sus detalles.
   */
  @Override
  @Transactional
  public CompraResponseDTO registrarCompra(final CompraRequestDTO dto) {

    log.info(LogMessages.START, SurtidoMessages.MODULE, SurtidoMessages.OP_REGISTRAR_COMPRA);

    Compra compra = Compra.builder().montoTotal(BigDecimal.ZERO).build();

    List<DetalleCompra> detallesCompra =
        dto.detallesCompra().stream().map(detalle -> crearDetalleCompra(compra, detalle)).toList();

    BigDecimal montoTotal =
        detallesCompra.stream()
            .map(DetalleCompra::getCostoTotalProducto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    compra.setMontoTotal(montoTotal);
    compra.setDetallesCompra(detallesCompra);

    Compra compraGuardada = compraRepository.save(compra);

    log.info(LogMessages.SUCCESS, SurtidoMessages.MODULE, SurtidoMessages.OP_REGISTRAR_COMPRA);

    return SurtidoMapper.toDTO(compraGuardada);
  }

  /**
   * Obtiene una compra por su identificador.
   *
   * @param id identificador de la compra.
   * @return compra encontrada.
   */
  @Override
  @Transactional(readOnly = true)
  public CompraResponseDTO obtenerCompraPorId(final Long id) {

    log.info(LogMessages.START, SurtidoMessages.MODULE, SurtidoMessages.OP_OBTENER_COMPRA);

    Compra compra =
        compraRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(SurtidoMessages.COMPRA_NO_ENCONTRADA));

    log.info(LogMessages.SUCCESS, SurtidoMessages.MODULE, SurtidoMessages.OP_OBTENER_COMPRA);

    return SurtidoMapper.toDTO(compra);
  }

  /**
   * Lista el historial de compras registradas.
   *
   * @return lista de compras registradas.
   */
  @Override
  @Transactional(readOnly = true)
  public List<CompraResponseDTO> listarCompras() {

    log.info(LogMessages.START, SurtidoMessages.MODULE, SurtidoMessages.OP_LISTAR_COMPRAS);

    List<CompraResponseDTO> compras =
        compraRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(SurtidoMapper::toDTO)
            .toList();

    log.info(LogMessages.SUCCESS, SurtidoMessages.MODULE, SurtidoMessages.OP_LISTAR_COMPRAS);

    return compras;
  }

  /**
   * Crea un detalle de compra y actualiza el producto correspondiente.
   *
   * @param compra compra a la que pertenece el detalle.
   * @param detalleDto información del producto comprado.
   * @return detalle de compra generado.
   */
  private DetalleCompra crearDetalleCompra(
      final Compra compra, final DetalleCompraRequestDTO detalleDto) {

    BigDecimal costoUnitarioCalculado =
        SurtidoCalculator.calcularCostoUnitario(
            detalleDto.costoTotalProducto(), detalleDto.cantidad());

    BigDecimal precioVenta =
        resolverPrecioVenta(
            costoUnitarioCalculado, detalleDto.tipoPrecio(), detalleDto.precioPersonalizado());

    Producto producto = obtenerOCrearProducto(detalleDto.nombreProducto(), precioVenta);

    producto.setCostoUnitarioActual(costoUnitarioCalculado);
    producto.setStockActual(producto.getStockActual() + detalleDto.cantidad());
    producto.setPrecioVenta(precioVenta);

    Producto productoGuardado = productoRepository.save(producto);

    return DetalleCompra.builder()
        .compra(compra)
        .producto(productoGuardado)
        .nombreProducto(producto.getNombre())
        .cantidad(detalleDto.cantidad())
        .costoTotalProducto(detalleDto.costoTotalProducto())
        .costoUnitarioCalculado(costoUnitarioCalculado)
        .precioVentaSeleccionado(precioVenta)
        .build();
  }

  /**
   * Determina el precio de venta según la opción seleccionada.
   *
   * @param costoUnitario costo unitario calculado.
   * @param tipoPrecio opción seleccionada por el usuario.
   * @param precioPersonalizado precio ingresado manualmente.
   * @return precio de venta definitivo.
   */
  private BigDecimal resolverPrecioVenta(
      final BigDecimal costoUnitario,
      final TipoPrecio tipoPrecio,
      final BigDecimal precioPersonalizado) {

    return switch (tipoPrecio) {
      case SUGERIDO_20 ->
          SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_20);

      case SUGERIDO_30 ->
          SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_30);

      case SUGERIDO_50 ->
          SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_50);

      case PERSONALIZADO -> validarYObtenerPrecioPersonalizado(precioPersonalizado);
    };
  }

  /**
   * Valida y devuelve el precio personalizado.
   *
   * @param precioPersonalizado precio ingresado manualmente.
   * @return precio personalizado validado.
   */
  private BigDecimal validarYObtenerPrecioPersonalizado(final BigDecimal precioPersonalizado) {

    if (precioPersonalizado == null) {
      throw new BusinessException(SurtidoMessages.PRECIO_PERSONALIZADO_REQUERIDO);
    }

    if (precioPersonalizado.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BusinessException(SurtidoMessages.PRECIO_PERSONALIZADO_POSITIVO);
    }

    return precioPersonalizado.setScale(
        BusinessConstants.DECIMAL_SCALE, BusinessConstants.DECIMAL_ROUNDING_MODE);
  }

  /**
   * Obtiene un producto existente por nombre o crea uno nuevo.
   *
   * @param nombreProducto nombre del producto.
   * @param precioVenta precio de venta calculado.
   * @return producto existente o producto nuevo.
   */
  private Producto obtenerOCrearProducto(
      final String nombreProducto, final BigDecimal precioVenta) {

    String nombreVisible = ProductoNombreNormalizer.limpiarNombreVisible(nombreProducto);

    String nombreComparacion = ProductoNombreNormalizer.normalizarParaComparacion(nombreVisible);

    Producto producto =
        productoRepository.findAll().stream()
            .filter(
                productoExistente ->
                    ProductoNombreNormalizer.normalizarParaComparacion(
                            productoExistente.getNombre())
                        .equals(nombreComparacion))
            .findFirst()
            .orElseGet(
                () ->
                    Producto.builder()
                        .nombre(nombreVisible)
                        .costoUnitarioActual(BigDecimal.ZERO)
                        .precioVenta(precioVenta)
                        .stockActual(0)
                        .stockMinimo(BusinessConstants.STOCK_BAJO_DEFAULT)
                        .activo(Boolean.TRUE)
                        .build());

    producto.setActivo(Boolean.TRUE);

    return producto;
  }
}
