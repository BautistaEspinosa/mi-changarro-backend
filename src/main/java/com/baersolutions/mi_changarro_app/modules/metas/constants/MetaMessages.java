package com.baersolutions.mi_changarro_app.modules.metas.constants;

/**
 * Constantes de mensajes del módulo Metas.
 *
 * <p>Centraliza mensajes de API, operaciones para logs, excepciones y
 * validaciones utilizadas por el módulo Metas.
 *
 * @author Baer Solutions
 */
public final class MetaMessages {

  public static final String MODULE = "METAS";

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
  public static final String OP_OBTENER = "OBTENER";
  public static final String OP_LISTAR = "LISTAR";
  public static final String OP_META_ACTIVA = "META_ACTIVA";
  public static final String OP_MOVIMIENTOS = "MOVIMIENTOS";
  public static final String OP_AHORRO_AUTOMATICO = "AHORRO_AUTOMATICO";
  public static final String META_CREADA =
      "Meta creada correctamente.";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */
  public static final String META_OBTENIDA =
      "Meta obtenida correctamente.";
  public static final String METAS_LISTADAS =
      "Metas obtenidas correctamente.";
  public static final String META_ACTIVA_OBTENIDA =
      "Meta activa obtenida correctamente.";
  public static final String MOVIMIENTOS_OBTENIDOS =
      "Movimientos de ahorro obtenidos correctamente.";
  public static final String META_NO_ENCONTRADA =
      "Meta no encontrada.";

  /*
   * ==========================
   * Excepciones
   * ==========================
   */
  public static final String META_ACTIVA_NO_ENCONTRADA =
      "No hay una meta activa.";
  public static final String FECHA_LIMITE_INVALIDA =
      "La fecha límite debe ser posterior a la fecha actual.";
  public static final String META_COMPLETADA =
      "La meta ya fue completada.";
  public static final String MONTO_AHORRO_INVALIDO =
      "El monto de ahorro debe ser mayor a cero.";
  public static final String NOMBRE_REQUERIDO =
      "El nombre de la meta es obligatorio.";

  /*
   * ==========================
   * Validaciones
   * ==========================
   */
  public static final String COSTO_OBJETIVO_REQUERIDO =
      "El costo objetivo es obligatorio.";
  public static final String COSTO_OBJETIVO_INVALIDO =
      "El costo objetivo debe ser mayor a cero.";
  public static final String FECHA_LIMITE_REQUERIDA =
      "La fecha límite es obligatoria.";

  private MetaMessages() {
    throw new IllegalStateException("Utility class");
  }

}