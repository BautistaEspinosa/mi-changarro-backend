package com.baersolutions.mi_changarro_app.modules.dashboard.constants;

/**
 * Centraliza los mensajes, operaciones y constantes textuales del módulo Dashboard.
 *
 * @author Roman Bautista Espinosa
 */
public final class DashboardMessages {

  /*
   * ==========================
   * Módulo
   * ==========================
   */

  public static final String MODULE = "DASHBOARD";

  /*
   * ==========================
   * Operaciones (Logs)
   * ==========================
   */

  public static final String OP_OBTENER = "OBTENER";

  /*
   * ==========================
   * Respuestas API
   * ==========================
   */

  public static final String DASHBOARD_OBTENIDO = "Dashboard obtenido correctamente.";

  private DashboardMessages() {
    throw new IllegalStateException("Utility class");
  }
}