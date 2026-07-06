package com.baersolutions.mi_changarro_app.modules.metas.enums;

/**
 * Enumeración que representa el origen de un movimiento de ahorro.
 *
 * <p>Permite identificar de dónde proviene el dinero que fue apartado
 * automáticamente para una meta.
 *
 * @author Baer Solutions
 */
public enum OrigenMovimientoAhorro {

  /**
   * Ahorro generado por una venta cobrada.
   */
  VENTA_COBRADA,

  /**
   * Ahorro generado por el pago de una deuda.
   */
  DEUDA_PAGADA

}