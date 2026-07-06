package com.baersolutions.mi_changarro_app.modules.ventas.enums;

/**
 * Enumeración que representa los tipos de venta permitidos por el sistema.
 *
 * <p>Define la forma en que se realiza el cobro de una venta:
 *
 * <ul>
 *   <li><b>CONTADO:</b> El cliente paga al momento de la venta.</li>
 *   <li><b>FIADO:</b> El cliente pagará posteriormente, generando una deuda
 *   que será administrada por el módulo Me Deben.</li>
 * </ul>
 *
 * @author Baer Solutions
 */
public enum TipoVenta {

  /**
   * Venta pagada al momento del registro.
   */
  CONTADO,

  /**
   * Venta pendiente de pago.
   */
  FIADO

}