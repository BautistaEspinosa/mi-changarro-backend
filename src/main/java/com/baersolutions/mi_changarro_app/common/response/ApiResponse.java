package com.baersolutions.mi_changarro_app.common.response;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.baersolutions.mi_changarro_app.common.util.RequestUtils;
/**
 * Respuesta estándar para toda la API.
 *
 * @param <T> Tipo de dato devuelto por el endpoint.
 */
public record ApiResponse<T>(

    String message,
    T data,
    int status,
    LocalDateTime timestamp,
    String path

) {

  /**
   * Construye una respuesta HTTP 200 (OK).
   *
   * @param message mensaje de la operación
   * @param data información devuelta
   * @return respuesta estándar
   */
  public static <T> ApiResponse<T> success(
      String message,
      T data
  ) {

    return build(
        message,
        data,
        HttpStatus.OK
    );
  }

  /**
   * Construye una respuesta HTTP 200 (OK) sin datos.
   *
   * @param message mensaje de la operación
   * @return respuesta estándar
   */
  public static ApiResponse<Void> success(
      String message
  ) {

    return build(
        message,
        null,
        HttpStatus.OK
    );
  }

  /**
   * Construye una respuesta HTTP 201 (Created).
   *
   * @param message mensaje de la operación
   * @param data recurso creado
   * @return respuesta estándar
   */
  public static <T> ApiResponse<T> created(
      String message,
      T data
  ) {

    return build(
        message,
        data,
        HttpStatus.CREATED
    );
  }

  /**
   * Construye la respuesta estándar.
   */
  private static <T> ApiResponse<T> build(
      String message,
      T data,
      HttpStatus status
  ) {

    return new ApiResponse<>(
        message,
        data,
        status.value(),
        LocalDateTime.now(),
        RequestUtils.getCurrentPath()
    );
  }

}