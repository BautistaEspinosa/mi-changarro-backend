package com.baersolutions.mi_changarro_app.modules.ventas.service;

import com.baersolutions.mi_changarro_app.modules.ventas.dto.request.VentaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaResponseDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 * Define las operaciones del módulo Ventas.
 *
 * <p>El módulo permite registrar ventas híbridas, consultar una venta
 * específica y listar el historial de ventas registradas.
 *
 * @author Baer Solutions
 */
public interface VentaService {

  /**
   * Registra una nueva venta.
   *
   * @param dto información de la venta a registrar.
   * @return venta registrada.
   */
  VentaResponseDTO registrarVenta(
      VentaRequestDTO dto
  );

  /**
   * Obtiene una venta por su identificador.
   *
   * @param id identificador de la venta.
   * @return venta encontrada.
   */
  VentaResponseDTO obtenerVentaPorId(
      Long id
  );

  /**
   * Lista el historial de ventas registradas.
   *
   * @return historial de ventas.
   */
  List<VentaResponseDTO> listarVentas();

  /**
   * Obtiene el total de ventas cobradas.
   *
   * @return total de ventas cobradas
   */
  BigDecimal obtenerTotalVentasCobradas();

  /**
   * Obtiene el total de ventas pendientes.
   *
   * @return total pendiente por cobrar
   */
  BigDecimal obtenerTotalVentasPendientes();

  /**
   * Obtiene los ingresos cobrados por productos propios.
   *
   * @return ingresos cobrados por productos propios
   */
  BigDecimal obtenerIngresosProductosPropios();

  /**
   * Obtiene los ingresos cobrados por Betterware.
   *
   * @return ingresos cobrados por Betterware
   */
  BigDecimal obtenerIngresosBetterware();

  /**
   * Obtiene el costo de reposición de los productos propios vendidos.
   *
   * @return costo de reposición de productos vendidos
   */
  BigDecimal obtenerCostoReposicionProductosVendidos();
}