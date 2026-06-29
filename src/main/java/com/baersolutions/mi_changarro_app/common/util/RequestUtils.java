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
   * Obtiene la URI de la petición actual.
   *
   * @return ruta de la petición o cadena vacía si no existe contexto HTTP.
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