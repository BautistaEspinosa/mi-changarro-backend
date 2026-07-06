package com.baersolutions.mi_changarro_app.modules.medeben.constants;

/**
 * Constantes del módulo Me Deben.
 *
 * <p>Centraliza mensajes de API, operaciones de logs, excepciones y
 * validaciones utilizadas por el módulo, evitando el uso de strings
 * hardcodeados.
 *
 * @author Baer Solutions
 */
public final class MeDebenMessages {

  /**
   * Constructor privado para evitar instanciación.
   */
  private MeDebenMessages() {
    throw new IllegalStateException("Utility class");
  }

  /*
   * ==========================
   * Módulo
   * ==========================
   */

  public static final String MODULE = "ME_DEBEN";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */

  public static final String OP_CREAR_DEUDA = "CREAR_DEUDA";

  public static final String OP_OBTENER_DEUDA = "OBTENER_DEUDA";

  public static final String OP_LISTAR_DEUDAS = "LISTAR_DEUDAS";

  public static final String OP_MARCAR_COMO_PAGADA = "MARCAR_COMO_PAGADA";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */

  public static final String DEUDA_CREADA =
      "Deuda creada correctamente.";

  public static final String DEUDA_OBTENIDA =
      "Deuda obtenida correctamente.";

  public static final String DEUDAS_OBTENIDAS =
      "Deudas obtenidas correctamente.";

  public static final String DEUDA_PAGADA =
      "La deuda fue marcada como pagada correctamente.";

  /*
   * ==========================
   * Excepciones
   * ==========================
   */

  public static final String DEUDA_NO_ENCONTRADA =
      "La deuda no fue encontrada.";

  public static final String DEUDA_YA_PAGADA =
      "La deuda ya fue pagada.";

  public static final String VENTA_NO_ES_FIADO =
      "Solo las ventas FIADO pueden generar una deuda.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */

  // Actualmente el módulo no requiere mensajes de validación propios.

}