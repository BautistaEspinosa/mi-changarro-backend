package com.baersolutions.mi_changarro_app.modules.medeben.enums;

/**
 * Enumeración que representa el estado de una deuda.
 *
 * <p>Permite identificar si una deuda generada por una venta FIADO sigue
 * pendiente de pago o si ya fue marcada como pagada desde el módulo Me Deben.
 *
 * @author Baer Solutions
 */
public enum EstadoDeuda {

  /**
   * Deuda pendiente de pago.
   */
  PENDIENTE,

  /**
   * Deuda liquidada por el cliente.
   */
  PAGADA

}