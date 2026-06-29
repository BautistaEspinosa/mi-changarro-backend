package com.baersolutions.mi_changarro_app.modules.inventario.constants;

/**
 * Mensajes y operaciones del módulo Inventario.
 */
public final class InventarioMessages {

  private InventarioMessages() {
    throw new IllegalStateException("Utility class");
  }

  public static final String MODULE = "INVENTARIO";

  // Operaciones
  public static final String OP_INVENTARIO_TOTAL = "INVENTARIO_TOTAL";
  public static final String OP_STOCK_BAJO = "STOCK_BAJO";
  public static final String OP_RESUMEN = "RESUMEN";

  // Mensajes de respuesta (API)
  public static final String MSG_INVENTARIO_ACTUAL = "Inventario actual";
  public static final String MSG_STOCK_BAJO = "Productos con stock bajo";
  public static final String MSG_RESUMEN = "Total de productos activos";
}