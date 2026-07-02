package com.baersolutions.mi_changarro_app.modules.surtido.constants;

/**
 * Centraliza todas las rutas REST del módulo Surtido.
 *
 * <p>El uso de esta clase evita rutas hardcodeadas en los controladores y
 * mantiene una única fuente de verdad para los endpoints del módulo.
 *
 * @author Baer Solutions
 */
public final class SurtidoRoutes {

  /**
   * Constructor privado para evitar instanciación.
   */
  private SurtidoRoutes() {
    throw new IllegalStateException("Utility class");
  }

  /*
   * ==========================
   * Rutas base
   * ==========================
   */

  /**
   * Ruta base del módulo Surtido.
   */
  public static final String BASE = "/api/surtido";

  /**
   * Ruta para obtener una compra por su identificador.
   */
  public static final String BY_ID = "/{id}";

  /*
   * ==========================
   * Endpoints del dominio
   * ==========================
   */

  /**
   * Ruta para consultar el historial de compras.
   */
  public static final String HISTORIAL = "/historial";

  /**
   * Ruta para consultar la última compra registrada.
   */
  public static final String ULTIMA_COMPRA = "/ultima-compra";
}