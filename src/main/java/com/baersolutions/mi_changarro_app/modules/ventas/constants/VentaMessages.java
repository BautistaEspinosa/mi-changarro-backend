package com.baersolutions.mi_changarro_app.modules.ventas.constants;

/**
 * Constantes de mensajes, operaciones, excepciones y validaciones del módulo Ventas.
 *
 * @author Baer Solutions
 */
public final class VentaMessages {

  public static final String MODULE = "VENTAS";

  /*
   * ==========================
   * Módulo
   * ==========================
   */
  public static final String OP_REGISTRAR_VENTA = "REGISTRAR_VENTA";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */
  public static final String OP_OBTENER_VENTA = "OBTENER_VENTA";
  public static final String OP_LISTAR_VENTAS = "LISTAR_VENTAS";
  public static final String VENTA_REGISTRADA =
      "Venta registrada correctamente.";
  public static final String OP_INDICADORES = "INDICADORES";
  /*
   * ==========================
   * Respuestas API
   * ==========================
   */
  public static final String VENTA_OBTENIDA =
      "Venta obtenida correctamente.";
  public static final String VENTAS_OBTENIDAS =
      "Ventas obtenidas correctamente.";
  public static final String VENTA_NO_ENCONTRADA =
      "Venta no encontrada.";

  /*
   * ==========================
   * Excepciones
   * ==========================
   */
  public static final String PRODUCTO_NO_ENCONTRADO =
      "Producto no encontrado.";
  public static final String PRODUCTO_INACTIVO =
      "El producto se encuentra inactivo.";
  public static final String STOCK_INSUFICIENTE =
      "No hay suficiente inventario para realizar la venta.";
  public static final String VENTA_SIN_PRODUCTOS_NI_BETTERWARE =
      "La venta debe incluir al menos un producto o un ingreso Betterware.";
  public static final String CLIENTE_REQUERIDO_PARA_FIADO =
      "El nombre del cliente es obligatorio para ventas fiadas.";
  public static final String TIPO_VENTA_REQUERIDO =
      "El tipo de venta es obligatorio.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */
  public static final String DETALLES_VENTA_NO_VACIOS =
      "La lista de productos no puede estar vacía.";
  public static final String PRODUCTO_ID_REQUERIDO =
      "El producto es obligatorio.";
  public static final String CANTIDAD_REQUERIDA =
      "La cantidad es obligatoria.";
  public static final String CANTIDAD_POSITIVA =
      "La cantidad debe ser mayor a cero.";
  public static final String MONTO_BETTERWARE_REQUERIDO =
      "El monto de Betterware es obligatorio.";
  public static final String MONTO_BETTERWARE_POSITIVO =
      "El monto de Betterware debe ser mayor a cero.";
  public static final String NOMBRE_CLIENTE_REQUERIDO =
      "El nombre del cliente es obligatorio.";

  private VentaMessages() {
    throw new IllegalStateException("Utility class");
  }
}