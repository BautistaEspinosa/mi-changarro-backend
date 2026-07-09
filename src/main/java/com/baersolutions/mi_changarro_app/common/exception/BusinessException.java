package com.baersolutions.mi_changarro_app.common.exception;

/**
 * Excepción lanzada cuando una regla de negocio impide completar una operación.
 *
 * <p>Representa errores funcionales del sistema que deben responderse con
 * HTTP 400.</p>
 */
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }
}
