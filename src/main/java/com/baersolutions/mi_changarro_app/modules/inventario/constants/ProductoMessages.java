package com.baersolutions.mi_changarro_app.modules.inventario.constants;

/**
 * Mensajes y constantes del módulo Producto.
 */
public final class ProductoMessages {

  public static final String MODULE = "PRODUCTO";

  /*
   * ==========================
   * Módulo
   * ==========================
   */
  public static final String OP_CREAR = "CREAR";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */
  public static final String OP_ACTUALIZAR = "ACTUALIZAR";
  public static final String OP_ELIMINAR = "ELIMINAR";
  public static final String OP_OBTENER = "OBTENER";
  public static final String OP_LISTAR = "LISTAR";
  public static final String OP_BUSCAR = "BUSCAR";
  public static final String OP_ACTIVAR = "ACTIVAR";
  public static final String PRODUCTO_CREADO =
      "Producto creado correctamente.";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */
  public static final String PRODUCTO_ACTUALIZADO =
      "Producto actualizado correctamente.";
  public static final String PRODUCTO_ELIMINADO =
      "Producto eliminado correctamente.";
  public static final String PRODUCTO_OBTENIDO =
      "Producto obtenido correctamente.";
  public static final String PRODUCTOS_LISTADOS =
      "Productos obtenidos correctamente.";
  public static final String PRODUCTO_NO_ENCONTRADO =
      "Producto no encontrado.";

  /*
   * ==========================
   * Excepciones
   * ==========================
   */
  public static final String PRODUCTO_INACTIVO =
      "El producto se encuentra inactivo.";
  public static final String PRODUCTO_DUPLICADO =
      "Ya existe un producto con ese nombre.";
  public static final String NOMBRE_REQUERIDO =
      "El nombre del producto es obligatorio.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */
  public static final String PRECIO_REQUERIDO =
      "El precio de venta es obligatorio.";
  public static final String COSTO_REQUERIDO =
      "El costo unitario es obligatorio.";
  public static final String STOCK_REQUERIDO =
      "El stock es obligatorio.";
  public static final String STOCK_MINIMO_REQUERIDO =
      "El stock mínimo es obligatorio.";

  private ProductoMessages() {
    throw new IllegalStateException("Utility class");
  }

}