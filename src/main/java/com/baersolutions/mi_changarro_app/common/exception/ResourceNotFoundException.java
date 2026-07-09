package com.baersolutions.mi_changarro_app.common.exception;

/**
 * Excepción lanzada cuando un recurso solicitado no existe.
 *
 * <p>Se utiliza para representar escenarios donde una entidad no puede ser
 * encontrada dentro del sistema y debe responderse con HTTP 404.</p>
 *
 * @author Baer Solutions
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * Crea una nueva excepción con el mensaje indicado.
   *
   * @param message descripción del error
   */
  public ResourceNotFoundException(
      String message
  ) {

    super(message);
  }

}