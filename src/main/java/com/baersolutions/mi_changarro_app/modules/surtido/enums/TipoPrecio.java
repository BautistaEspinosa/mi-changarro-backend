package com.baersolutions.mi_changarro_app.modules.surtido.enums;

/**
 * Representa las opciones disponibles para definir el precio de venta de un producto durante el
 * proceso de surtido.
 *
 * <p>Las opciones sugeridas utilizan los márgenes definidos en {@code BusinessConstants}. La opción
 * personalizada permite que el usuario capture manualmente el precio de venta.
 *
 * @author Baer Solutions
 */
public enum TipoPrecio {

  /** Precio calculado utilizando un margen del 20 %. */
  SUGERIDO_20,

  /** Precio calculado utilizando un margen del 30 %. */
  SUGERIDO_30,

  /** Precio calculado utilizando un margen del 50 %. */
  SUGERIDO_50,

  /** Precio capturado manualmente por el usuario. */
  PERSONALIZADO
}
