package com.baersolutions.mi_changarro_app.common.constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Constantes de negocio compartidas por los módulos de la aplicación.
 *
 * <p>Centraliza valores reutilizables relacionados con reglas generales del
 * sistema, como multiplicadores de sugerencia de precio, escala decimal,
 * redondeo monetario y valores base utilizados por más de un módulo.
 *
 * @author Baer Solutions
 */
public final class BusinessConstants {

  private BusinessConstants() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Cantidad por defecto para considerar un producto con stock bajo.
   */
  public static final int STOCK_BAJO_DEFAULT = 5;
  public static final int DECIMAL_SCALE = 2;

  public static final RoundingMode DECIMAL_ROUNDING_MODE =
      RoundingMode.HALF_UP;

  /**
   * Márgenes sugeridos para cálculo de precio de venta.
   */
  public static final BigDecimal MARGEN_20 = BigDecimal.valueOf(1.20);

  public static final BigDecimal MARGEN_30 = BigDecimal.valueOf(1.30);

  public static final BigDecimal MARGEN_50 = BigDecimal.valueOf(1.50);

}