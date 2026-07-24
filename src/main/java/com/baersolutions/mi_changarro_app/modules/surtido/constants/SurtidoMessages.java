package com.baersolutions.mi_changarro_app.modules.surtido.constants;

/**
 * Constantes de mensajes del módulo Surtido.
 *
 * <p>Centraliza los mensajes utilizados por el módulo, incluyendo:
 *
 * <ul>
 *   <li>Nombre del módulo.
 *   <li>Operaciones para registro de logs.
 *   <li>Mensajes de respuesta de la API.
 *   <li>Mensajes de excepciones.
 *   <li>Mensajes de validación.
 * </ul>
 *
 * <p>Con esto se garantiza el cumplimiento de la política de cero strings hardcodeados dentro del
 * módulo.
 *
 * @author Baer Solutions
 */
public final class SurtidoMessages {

  public static final String MODULE = "SURTIDO";

  /*
   * ==========================
   * Módulo
   * ==========================
   */
  public static final String OP_REGISTRAR_COMPRA = "REGISTRAR_COMPRA";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */
  public static final String OP_OBTENER_COMPRA = "OBTENER_COMPRA";
  public static final String OP_LISTAR_COMPRAS = "LISTAR_COMPRAS";
  public static final String COMPRA_REGISTRADA = "Compra registrada correctamente.";
  public static final String OP_CALCULAR_PRECIOS = "CALCULAR_PRECIOS";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */
  public static final String COMPRA_OBTENIDA = "Compra obtenida correctamente.";
  public static final String COMPRAS_OBTENIDAS = "Compras obtenidas correctamente.";
  public static final String COMPRA_NO_ENCONTRADA = "La compra no fue encontrada.";
  public static final String PRECIOS_CALCULADOS = "Precios sugeridos calculados correctamente.";
  /*
   * ==========================
   * Excepciones
   * ==========================
   */
  public static final String PRODUCTO_NO_ENCONTRADO = "El producto no fue encontrado.";
  public static final String CANTIDAD_INVALIDA = "La cantidad debe ser mayor a cero.";
  public static final String COSTO_TOTAL_INVALIDO = "El costo total debe ser mayor a cero.";
  public static final String PRODUCTO_REQUERIDO = "El producto es obligatorio.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */
  public static final String CANTIDAD_REQUERIDA = "La cantidad es obligatoria.";
  public static final String CANTIDAD_POSITIVA = "La cantidad debe ser mayor a cero.";
  public static final String COSTO_TOTAL_REQUERIDO = "El costo total es obligatorio.";
  public static final String COSTO_TOTAL_POSITIVO = "El costo total debe ser mayor a cero.";
  public static final String COSTO_TOTAL_PRODUCTO_REQUERIDO =
      "El costo total del producto es obligatorio.";
  public static final String DETALLES_COMPRA_REQUERIDOS =
      "La compra debe contener al menos un producto.";
  public static final String DETALLES_COMPRA_NO_VACIOS =
      "La compra debe contener al menos un producto.";
  public static final String PRECIO_VENTA_REQUERIDO = "El precio de venta es obligatorio.";
  public static final String PRECIO_VENTA_POSITIVO = "El precio de venta debe ser mayor a cero.";
  public static final String TIPO_PRECIO_REQUERIDO = "Debe seleccionar un tipo de precio.";

  public static final String PRECIO_PERSONALIZADO_REQUERIDO =
      "Debe ingresar un precio personalizado.";

  public static final String PRECIO_PERSONALIZADO_POSITIVO =
      "El precio personalizado debe ser mayor a cero.";

  /** Constructor privado para evitar instanciación. */
  private SurtidoMessages() {
    throw new IllegalStateException("Utility class");
  }
}
