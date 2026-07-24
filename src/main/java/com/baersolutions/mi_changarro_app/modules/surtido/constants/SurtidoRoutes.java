package com.baersolutions.mi_changarro_app.modules.surtido.constants;

/**
 * Centraliza todas las rutas REST del módulo Surtido.
 *
 * <p>El uso de esta clase evita rutas hardcodeadas en los controladores y mantiene una única fuente
 * de verdad para los endpoints del módulo.
 *
 * @author Baer Solutions
 */
public final class SurtidoRoutes {

  /** Ruta base del módulo Surtido. */
  public static final String BASE = "/api/surtido";

  /*
   * ==========================
   * Rutas base
   * ==========================
   */
  /** Ruta para obtener una compra por su identificador. */
  public static final String BY_ID = "/{id}";

  /** Ruta para consultar el historial de compras. */
  public static final String HISTORIAL = "/historial";

  /** Ruta para calcular los precios sugeridos de un producto. */
  public static final String CALCULAR_PRECIOS = "/calcular-precios";

  /*
   * ==========================
   * Endpoints del dominio
   * ==========================
   */

  /** Constructor privado para evitar instanciación. */
  private SurtidoRoutes() {
    throw new IllegalStateException("Utility class");
  }
}
