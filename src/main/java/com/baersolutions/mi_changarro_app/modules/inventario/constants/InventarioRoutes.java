package com.baersolutions.mi_changarro_app.modules.inventario.constants;

/**
 * Rutas del módulo Inventario.
 */
public final class InventarioRoutes {

  private InventarioRoutes() {
    throw new IllegalStateException("Utility class");
  }

  public static final String BASE = "/api/inventario";

  public static final String STOCK_BAJO = "/stock-bajo";

  public static final String RESUMEN = "/resumen";

}