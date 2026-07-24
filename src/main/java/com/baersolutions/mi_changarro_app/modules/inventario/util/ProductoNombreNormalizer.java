package com.baersolutions.mi_changarro_app.modules.inventario.util;

import java.text.Normalizer;
import java.util.Locale;

/**
 * Utilidad para normalizar nombres de productos durante búsquedas y comparaciones.
 *
 * <p>La normalización ignora diferencias de mayúsculas, minúsculas, acentos y espacios repetidos,
 * sin modificar el nombre visible almacenado en la base de datos.
 *
 * @author Baer Solutions
 */
public final class ProductoNombreNormalizer {

  private ProductoNombreNormalizer() {}

  /**
   * Normaliza un nombre de producto para realizar comparaciones.
   *
   * @param nombre nombre que se desea normalizar.
   * @return nombre normalizado.
   */
  public static String normalizarParaComparacion(final String nombre) {

    if (nombre == null) {
      return "";
    }

    String sinEspaciosRepetidos = nombre.trim().replaceAll("\\s+", " ");

    String sinAcentos =
        Normalizer.normalize(sinEspaciosRepetidos, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

    return sinAcentos.toLowerCase(Locale.ROOT);
  }

  /**
   * Limpia el nombre visible que será almacenado.
   *
   * <p>Conserva acentos, mayúsculas y caracteres propios del nombre, pero elimina espacios externos
   * y espacios internos repetidos.
   *
   * @param nombre nombre ingresado por el usuario.
   * @return nombre limpio para almacenamiento.
   */
  public static String limpiarNombreVisible(final String nombre) {

    if (nombre == null) {
      return null;
    }

    return nombre.trim().replaceAll("\\s+", " ");
  }
}
