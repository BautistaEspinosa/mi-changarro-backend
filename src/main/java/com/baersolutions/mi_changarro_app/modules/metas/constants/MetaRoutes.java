package com.baersolutions.mi_changarro_app.modules.metas.constants;

/**
 * Constantes de rutas REST del módulo Metas.
 *
 * <p>Centraliza los paths utilizados por el controlador del módulo para evitar
 * rutas hardcodeadas.
 *
 * @author Baer Solutions
 */
public final class MetaRoutes {

  /**
   * Ruta base del módulo Metas.
   */
  public static final String BASE = "/api/metas";
  /**
   * Ruta para obtener un recurso por identificador.
   */
  public static final String BY_ID = "/{id}";
  /**
   * Ruta para consultar la meta activa.
   */
  public static final String ACTIVA = "/activa";
  /**
   * Ruta para consultar el historial de metas.
   */
  public static final String HISTORIAL = "/historial";
  /**
   * Ruta para consultar los movimientos de ahorro de una meta.
   */
  public static final String MOVIMIENTOS = "/{id}/movimientos";

  private MetaRoutes() {
    throw new IllegalStateException("Utility class");
  }

}