package com.baersolutions.mi_changarro_app.modules.inventario.constants;

/**
 * Rutas del módulo Producto.
 */
public final class ProductoRoutes {

  private ProductoRoutes() {
    throw new IllegalStateException("Utility class");
  }

  public static final String BASE = "/api/productos";

  public static final String BY_ID = "/{id}";
  public static final String BUSCAR = "/buscar";
  public static final String ACTIVAR = "/{id}/activar";
  public static final String DESACTIVAR = "/{id}/desactivar";

}