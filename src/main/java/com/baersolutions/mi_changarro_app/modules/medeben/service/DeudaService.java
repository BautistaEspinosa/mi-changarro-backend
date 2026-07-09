package com.baersolutions.mi_changarro_app.modules.medeben.service;

import com.baersolutions.mi_changarro_app.modules.medeben.dto.response.DeudaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import java.math.BigDecimal;
import java.util.List;

/**
 * Define las operaciones del módulo Me Deben.
 *
 * <p>Permite crear deudas automáticamente desde ventas FIADO, consultar las
 * deudas registradas y marcar una deuda como pagada.
 *
 * @author Baer Solutions
 */
public interface DeudaService {

  /**
   * Crea una deuda a partir de una venta FIADO.
   *
   * <p>Esta operación es utilizada por el módulo Ventas como punto de
   * integración cuando se registra una venta pendiente de pago.
   *
   * @param venta venta FIADO que originará la deuda.
   * @return deuda creada.
   */
  DeudaResponseDTO crearDesdeVenta(
      Venta venta
  );

  /**
   * Obtiene una deuda por su identificador.
   *
   * @param id identificador de la deuda.
   * @return deuda encontrada.
   */
  DeudaResponseDTO obtenerPorId(
      Long id
  );

  /**
   * Obtiene todas las deudas registradas.
   *
   * @return lista de deudas.
   */
  List<DeudaResponseDTO> listarDeudas();

  /**
   * Obtiene únicamente las deudas pendientes.
   *
   * @return lista de deudas pendientes.
   */
  List<DeudaResponseDTO> listarPendientes();

  /**
   * Marca una deuda como pagada.
   *
   * <p>Esta operación actualiza únicamente el estado de la deuda. La venta que
   * originó la deuda permanece sin modificaciones.
   *
   * @param id identificador de la deuda.
   * @return deuda actualizada.
   */
  DeudaResponseDTO marcarComoPagada(
      Long id
  );

  /**
   * Obtiene el dinero que aún está pendiente por cobrar.
   *
   * @return monto total pendiente por cobrar
   */
  BigDecimal obtenerDineroPendientePorCobrar();

  /**
   * Obtiene el dinero recuperado por el pago de deudas.
   *
   * @return monto total recuperado
   */
  BigDecimal obtenerDineroRecuperado();
}