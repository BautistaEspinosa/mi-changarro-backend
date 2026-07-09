package com.baersolutions.mi_changarro_app.common.exception;

/**
 * Excepción lanzada cuando una operación es válida técnicamente, pero no puede ejecutarse debido al
 * estado actual del negocio.
 *
 * <p>Se traduce en una respuesta HTTP 409 (Conflict).</p>
 */
public class InvalidOperationException extends RuntimeException {

  public InvalidOperationException(String message) {
    super(message);
  }
}
