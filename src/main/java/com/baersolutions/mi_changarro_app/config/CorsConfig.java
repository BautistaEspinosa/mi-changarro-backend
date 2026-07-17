package com.baersolutions.mi_changarro_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS para la comunicación entre el Frontend y los endpoints públicos del
 * Backend.
 *
 * <p>El origen permitido se obtiene desde la configuración de la aplicación, evitando mantener
 * direcciones del Frontend directamente en el código.
 *
 * @author Roman Bautista Espinosa
 */
@Configuration
public class CorsConfig {

  private static final String API_PATH_PATTERN = "/api/**";

  private static final String[] ALLOWED_METHODS = {
    "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
  };

  private final String allowedOrigin;

  /**
   * Construye la configuración de CORS con el origen permitido.
   *
   * @param allowedOrigin dirección permitida para consumir el Backend
   */
  public CorsConfig(@Value("${app.cors.allowed-origin}") final String allowedOrigin) {
    this.allowedOrigin = allowedOrigin;
  }

  /**
   * Registra la configuración global de CORS para la API.
   *
   * @return configuración MVC con soporte de CORS
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {

    return new WebMvcConfigurer() {

      /**
       * Agrega las reglas de CORS para los endpoints de la aplicación.
       *
       * @param registry registro de configuraciones CORS
       */
      @Override
      public void addCorsMappings(final CorsRegistry registry) {

        registry
            .addMapping(API_PATH_PATTERN)
            .allowedOrigins(allowedOrigin)
            .allowedMethods(ALLOWED_METHODS)
            .allowedHeaders("*");
      }
    };
  }
}
