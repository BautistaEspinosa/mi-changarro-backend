package com.baersolutions.mi_changarro_app.modules.medeben.constants;

/**
 * Rutas REST del módulo Me Deben.
 *
 * <p>Centraliza los paths utilizados por el controlador del módulo para
 * evitar rutas hardcodeadas y mantener consistencia con la arquitectura del
 * proyecto.
 *
 * @author Baer Solutions
 */
public final class MeDebenRoutes {

  /**
   * Constructor privado para evitar instanciación.
   */
  private MeDebenRoutes() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Ruta base del módulo Me Deben.
   */
  public static final String BASE = "/api/me-deben";

  /**
   * Ruta para consultar una deuda por identificador.
   */
  public static final String BY_ID = "/{id}";

  /**
   * Ruta para marcar una deuda como pagada.
   */
  public static final String PAGAR = "/{id}/pagar";

  /**
   * Ruta para consultar únicamente deudas pendientes.
   */
  public static final String PENDIENTES = "/pendientes";

}