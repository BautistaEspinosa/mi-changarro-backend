package com.baersolutions.mi_changarro_app.common.constants;

/**
 * Plantillas de logs reutilizables.
 */
public final class LogMessages {

  private LogMessages() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Inicio de una operación.
   */
  public static final String START =
      "[{}] [{}] Inicio de operación.";

  /**
   * Operación finalizada correctamente.
   */
  public static final String SUCCESS =
      "[{}] [{}] Operación finalizada correctamente.";

  /**
   * Operación con error.
   */
  public static final String ERROR =
      "[{}] [{}] Error: {}";

  /**
   * Registro encontrado.
   */
  public static final String FOUND =
      "[{}] [{}] Registro encontrado. ID: {}";

  /**
   * Registro no encontrado.
   */
  public static final String NOT_FOUND =
      "[{}] [{}] Registro no encontrado. ID: {}";

}