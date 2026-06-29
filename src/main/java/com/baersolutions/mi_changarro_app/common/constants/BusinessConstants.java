package com.baersolutions.mi_changarro_app.common.constants;

import java.math.BigDecimal;

/**
 * Constantes de negocio compartidas entre módulos.
 */
public final class BusinessConstants {

  private BusinessConstants() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Cantidad por defecto para considerar un producto con stock bajo.
   */
  public static final int STOCK_BAJO_DEFAULT = 5;

  /**
   * Márgenes sugeridos para cálculo de precio de venta.
   */
  public static final BigDecimal MARGEN_20 = BigDecimal.valueOf(1.20);

  public static final BigDecimal MARGEN_30 = BigDecimal.valueOf(1.30);

  public static final BigDecimal MARGEN_50 = BigDecimal.valueOf(1.50);

}