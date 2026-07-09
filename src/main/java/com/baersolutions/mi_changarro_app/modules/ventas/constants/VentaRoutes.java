package com.baersolutions.mi_changarro_app.modules.ventas.constants;

/**
 * Constantes de rutas REST del módulo Ventas.
 *
 * <p>Centraliza los endpoints utilizados por el controlador de ventas para
 * evitar rutas hardcodeadas y mantener consistencia con el resto de módulos.
 *
 * @author Baer Solutions
 */
public final class VentaRoutes {

  public static final String BASE = "/api/ventas";
  public static final String BY_ID = "/{id}";
  public static final String HISTORIAL = "/historial";

  private VentaRoutes() {
    throw new IllegalStateException("Utility class");
  }
}