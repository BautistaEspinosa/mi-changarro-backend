package com.baersolutions.mi_changarro_app.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Utilidades relacionadas con la petición HTTP actual.
 */
public final class RequestUtils {

  private RequestUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Utilidad para obtener información de la petición HTTP actual.
   *
   * <p>Se utiliza principalmente para incluir la ruta solicitada dentro de las
   * respuestas estándar de la API.</p>
   *
   * @author Baer Solutions
   */
  public static String getCurrentPath() {

    RequestAttributes attributes =
        RequestContextHolder.getRequestAttributes();

    if (attributes instanceof ServletRequestAttributes servletAttributes) {

      HttpServletRequest request =
          servletAttributes.getRequest();

      return request.getRequestURI();
    }

    return "";
  }

}