package com.baersolutions.mi_changarro_app.modules.inventario.constants;

/**
 * Mensajes y constantes del módulo Producto.
 */
public final class ProductoMessages {

  private ProductoMessages() {
    throw new IllegalStateException("Utility class");
  }

  /*
   * ==========================
   * Módulo
   * ==========================
   */

  public static final String MODULE = "PRODUCTO";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */

  public static final String OP_CREAR = "CREAR";

  public static final String OP_ACTUALIZAR = "ACTUALIZAR";

  public static final String OP_ELIMINAR = "ELIMINAR";

  public static final String OP_OBTENER = "OBTENER";

  public static final String OP_LISTAR = "LISTAR";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */

  public static final String PRODUCTO_CREADO =
      "Producto creado correctamente.";

  public static final String PRODUCTO_ACTUALIZADO =
      "Producto actualizado correctamente.";

  public static final String PRODUCTO_ELIMINADO =
      "Producto eliminado correctamente.";

  public static final String PRODUCTO_OBTENIDO =
      "Producto obtenido correctamente.";

  public static final String PRODUCTOS_LISTADOS =
      "Productos obtenidos correctamente.";

  /*
   * ==========================
   * Excepciones
   * ==========================
   */

  public static final String PRODUCTO_NO_ENCONTRADO =
      "Producto no encontrado.";

  public static final String PRODUCTO_INACTIVO =
      "El producto se encuentra inactivo.";

  public static final String PRODUCTO_DUPLICADO =
      "Ya existe un producto con ese nombre.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */

  public static final String NOMBRE_REQUERIDO =
      "El nombre del producto es obligatorio.";

  public static final String PRECIO_REQUERIDO =
      "El precio de venta es obligatorio.";

  public static final String COSTO_REQUERIDO =
      "El costo unitario es obligatorio.";

  public static final String STOCK_REQUERIDO =
      "El stock es obligatorio.";

  public static final String STOCK_MINIMO_REQUERIDO =
      "El stock mínimo es obligatorio.";

}