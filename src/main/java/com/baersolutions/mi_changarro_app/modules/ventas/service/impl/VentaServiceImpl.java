package com.baersolutions.mi_changarro_app.modules.ventas.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.medeben.service.DeudaService;
import com.baersolutions.mi_changarro_app.modules.metas.service.MetaService;
import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaMessages;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.request.DetalleVentaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.request.VentaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.DetalleVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.VentaBetterware;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.EstadoVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.TipoVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.mapper.VentaMapper;
import com.baersolutions.mi_changarro_app.modules.ventas.repository.VentaRepository;
import com.baersolutions.mi_changarro_app.modules.ventas.service.VentaService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Ventas.
 *
 * <p>Contiene la lógica de negocio para registrar ventas híbridas, reducir
 * inventario de productos propios, calcular totales, conservar snapshots de
 * precios y costos, y consultar ventas registradas.
 *
 * @author Baer Solutions
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

  private final VentaRepository ventaRepository;

  private final ProductoRepository productoRepository;

  private final DeudaService deudaService;

  private final MetaService metaService;

  /**
   * Registra una nueva venta híbrida.
   *
   * <p>La venta puede contener productos propios, ingreso Betterware o ambos.
   * Solo los productos propios reducen inventario. Betterware únicamente suma
   * al total de la venta.
   *
   * @param dto información de la venta a registrar.
   * @return venta registrada.
   */
  @Override
  @Transactional
  public VentaResponseDTO registrarVenta(final VentaRequestDTO dto) {

    log.info(
        LogMessages.START,
        VentaMessages.MODULE,
        VentaMessages.OP_REGISTRAR_VENTA
    );

    validarVenta(dto);

    Venta venta = Venta.builder()
        .tipoVenta(dto.tipoVenta())
        .estadoVenta(calcularEstadoVenta(dto.tipoVenta()))
        .nombreCliente(dto.nombreCliente())
        .totalVenta(BigDecimal.ZERO)
        .build();

    List<DetalleVenta> detallesVenta = crearDetallesVenta(
        venta,
        dto.detallesVenta()
    );

    VentaBetterware ventaBetterware = crearVentaBetterware(
        venta,
        dto
    );

    BigDecimal totalProductos = detallesVenta.stream()
        .map(DetalleVenta::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalBetterware = Objects.nonNull(ventaBetterware)
        ? ventaBetterware.getMontoTotal()
        : BigDecimal.ZERO;

    venta.setDetallesVenta(detallesVenta);
    venta.setVentaBetterware(ventaBetterware);
    venta.setTotalVenta(totalProductos.add(totalBetterware));

    Venta ventaGuardada = ventaRepository.save(venta);

    if (TipoVenta.FIADO.equals(dto.tipoVenta())) {
      deudaService.crearDesdeVenta(ventaGuardada);
    }

    if(EstadoVenta.COBRADO.equals(ventaGuardada.getEstadoVenta())){
      metaService.registrarAhorroDesdeVenta(ventaGuardada);
    }

    log.info(
        LogMessages.SUCCESS,
        VentaMessages.MODULE,
        VentaMessages.OP_REGISTRAR_VENTA
    );

    return VentaMapper.toDTO(ventaGuardada);
  }

  /**
   * Obtiene una venta por su identificador.
   *
   * @param id identificador de la venta.
   * @return venta encontrada.
   */
  @Override
  @Transactional(readOnly = true)
  public VentaResponseDTO obtenerVentaPorId(final Long id) {

    log.info(
        LogMessages.START,
        VentaMessages.MODULE,
        VentaMessages.OP_OBTENER_VENTA
    );

    Venta venta = ventaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            VentaMessages.VENTA_NO_ENCONTRADA
        ));

    log.info(
        LogMessages.SUCCESS,
        VentaMessages.MODULE,
        VentaMessages.OP_OBTENER_VENTA
    );

    return VentaMapper.toDTO(venta);
  }

  /**
   * Lista el historial de ventas registradas.
   *
   * @return historial de ventas.
   */
  @Override
  @Transactional(readOnly = true)
  public List<VentaResponseDTO> listarVentas() {

    log.info(
        LogMessages.START,
        VentaMessages.MODULE,
        VentaMessages.OP_LISTAR_VENTAS
    );

    List<VentaResponseDTO> ventas = ventaRepository.findAllByOrderByCreatedAtDesc()
        .stream()
        .map(VentaMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        VentaMessages.MODULE,
        VentaMessages.OP_LISTAR_VENTAS
    );

    return ventas;
  }

  /**
   * Valida reglas generales para registrar una venta.
   *
   * @param dto información de la venta.
   */
  private void validarVenta(final VentaRequestDTO dto) {

    boolean sinProductos = dto.detallesVenta() == null
        || dto.detallesVenta().isEmpty();

    boolean sinBetterware = dto.ventaBetterware() == null;

    if (sinProductos && sinBetterware) {
      throw new BusinessException(
          VentaMessages.VENTA_SIN_PRODUCTOS_NI_BETTERWARE
      );
    }

    if (TipoVenta.FIADO.equals(dto.tipoVenta())
        && (dto.nombreCliente() == null || dto.nombreCliente().isBlank())) {
      throw new BusinessException(
          VentaMessages.CLIENTE_REQUERIDO_PARA_FIADO
      );
    }
  }

  /**
   * Calcula el estado inicial de la venta.
   *
   * @param tipoVenta tipo de venta.
   * @return estado correspondiente.
   */
  private EstadoVenta calcularEstadoVenta(final TipoVenta tipoVenta) {

    if (TipoVenta.FIADO.equals(tipoVenta)) {
      return EstadoVenta.PENDIENTE;
    }

    return EstadoVenta.COBRADO;
  }

  /**
   * Crea los detalles de productos propios de la venta.
   *
   * @param venta venta a la que pertenecen los detalles.
   * @param detallesDto productos incluidos en la venta.
   * @return detalles generados.
   */
  private List<DetalleVenta> crearDetallesVenta(
      final Venta venta,
      final List<DetalleVentaRequestDTO> detallesDto
  ) {

    if (detallesDto == null || detallesDto.isEmpty()) {
      return List.of();
    }

    return detallesDto.stream()
        .map(detalle -> crearDetalleVenta(venta, detalle))
        .toList();
  }

  /**
   * Crea un detalle de venta y reduce el inventario del producto.
   *
   * @param venta venta a la que pertenece el detalle.
   * @param detalleDto información del producto vendido.
   * @return detalle de venta generado.
   */
  private DetalleVenta crearDetalleVenta(
      final Venta venta,
      final DetalleVentaRequestDTO detalleDto
  ) {

    Producto producto = productoRepository.findById(detalleDto.productoId())
        .orElseThrow(() -> new ResourceNotFoundException(
            VentaMessages.PRODUCTO_NO_ENCONTRADO
        ));

    validarProducto(producto, detalleDto.cantidad());

    BigDecimal precioUnitario = producto.getPrecioVenta();
    BigDecimal costoUnitarioSnapshot = producto.getCostoUnitarioActual();

    BigDecimal subtotal = precioUnitario
        .multiply(BigDecimal.valueOf(detalleDto.cantidad()))
        .setScale(
            BusinessConstants.DECIMAL_SCALE,
            BusinessConstants.DECIMAL_ROUNDING_MODE
        );

    producto.setStockActual(producto.getStockActual() - detalleDto.cantidad());
    Producto productoGuardado = productoRepository.save(producto);

    return DetalleVenta.builder()
        .venta(venta)
        .producto(productoGuardado)
        .cantidad(detalleDto.cantidad())
        .precioUnitario(precioUnitario)
        .costoUnitarioSnapshot(costoUnitarioSnapshot)
        .subtotal(subtotal)
        .build();
  }

  /**
   * Valida que el producto pueda venderse.
   *
   * @param producto producto a validar.
   * @param cantidad cantidad solicitada.
   */
  private void validarProducto(
      final Producto producto,
      final Integer cantidad
  ) {

    if (Boolean.FALSE.equals(producto.getActivo())) {
      throw new BusinessException(
          VentaMessages.PRODUCTO_INACTIVO
      );
    }

    if (producto.getStockActual() < cantidad) {
      throw new BusinessException(
          VentaMessages.STOCK_INSUFICIENTE
      );
    }
  }

  /**
   * Crea el ingreso Betterware asociado a la venta cuando aplica.
   *
   * @param venta venta a la que pertenece el ingreso Betterware.
   * @param dto información de la venta.
   * @return ingreso Betterware o {@code null}.
   */
  private VentaBetterware crearVentaBetterware(
      final Venta venta,
      final VentaRequestDTO dto
  ) {

    if (dto.ventaBetterware() == null) {
      return null;
    }

    return VentaBetterware.builder()
        .venta(venta)
        .montoTotal(dto.ventaBetterware().montoTotal())
        .build();
  }

}