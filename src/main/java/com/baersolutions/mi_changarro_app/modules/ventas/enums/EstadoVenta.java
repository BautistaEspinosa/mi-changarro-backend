package com.baersolutions.mi_changarro_app.modules.ventas.enums;

/**
 * Enumeración que representa el estado de una venta.
 *
 * <p>El estado es determinado automáticamente por el sistema según el tipo
 * de venta registrado:
 *
 * <ul>
 *   <li><b>COBRADO:</b> La venta fue pagada al momento de su registro.</li>
 *   <li><b>PENDIENTE:</b> La venta quedó pendiente de pago por tratarse de
 *   una venta a crédito (FIADO).</li>
 * </ul>
 *
 * <p>El estado no es capturado por el usuario; forma parte de la lógica de
 * negocio implementada por el módulo Ventas.
 *
 * @author Baer Solutions
 */
public enum EstadoVenta {

  /**
   * Venta liquidada al momento de su registro.
   */
  COBRADO,

  /**
   * Venta pendiente de pago.
   */
  PENDIENTE

}