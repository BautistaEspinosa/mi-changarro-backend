package com.baersolutions.mi_changarro_app.modules.surtido.util;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import java.math.BigDecimal;

/**
 * Centraliza los cálculos monetarios utilizados por el módulo Surtido.
 *
 * <p>Permite reutilizar las mismas reglas para calcular costos unitarios y precios sugeridos
 * durante la previsualización, el registro de compras y la construcción de respuestas.
 *
 * @author Baer Solutions
 */
public final class SurtidoCalculator {

  /** Constructor privado para evitar instanciación. */
  private SurtidoCalculator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Calcula el costo unitario de un producto.
   *
   * @param costoTotalProducto costo total pagado por el producto.
   * @param cantidad cantidad adquirida.
   * @return costo unitario calculado.
   */
  public static BigDecimal calcularCostoUnitario(
      final BigDecimal costoTotalProducto, final Integer cantidad) {

    return costoTotalProducto.divide(
        BigDecimal.valueOf(cantidad),
        BusinessConstants.DECIMAL_SCALE,
        BusinessConstants.DECIMAL_ROUNDING_MODE);
  }

  /**
   * Calcula un precio sugerido utilizando el costo unitario y un margen.
   *
   * @param costoUnitario costo unitario del producto.
   * @param margen multiplicador utilizado para calcular el precio.
   * @return precio sugerido.
   */
  public static BigDecimal calcularPrecioSugerido(
      final BigDecimal costoUnitario, final BigDecimal margen) {

    return costoUnitario
        .multiply(margen)
        .setScale(BusinessConstants.DECIMAL_SCALE, BusinessConstants.DECIMAL_ROUNDING_MODE);
  }
}
