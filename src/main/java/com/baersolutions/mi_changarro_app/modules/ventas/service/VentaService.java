package com.baersolutions.mi_changarro_app.modules.ventas.service;

import com.baersolutions.mi_changarro_app.modules.ventas.dto.request.VentaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaResponseDTO;
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
}