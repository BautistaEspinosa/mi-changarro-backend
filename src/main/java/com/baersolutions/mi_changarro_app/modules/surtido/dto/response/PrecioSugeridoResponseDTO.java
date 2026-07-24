package com.baersolutions.mi_changarro_app.modules.surtido.dto.response;

import java.math.BigDecimal;

/**
 * DTO que contiene el resultado del cálculo de precios sugeridos para un producto.
 *
 * <p>Los valores se generan utilizando el costo unitario calculado y los márgenes definidos en
 * {@code BusinessConstants}.
 *
 * <p>Esta información se utiliza únicamente como previsualización y no se almacena en la base de
 * datos.
 *
 * @param costoUnitarioCalculado costo unitario obtenido al dividir el costo total entre la
 *     cantidad.
 * @param precioSugerido20 precio sugerido con margen del 20 %.
 * @param precioSugerido30 precio sugerido con margen del 30 %.
 * @param precioSugerido50 precio sugerido con margen del 50 %.
 * @author Baer Solutions
 */
public record PrecioSugeridoResponseDTO(
    BigDecimal costoUnitarioCalculado,
    BigDecimal precioSugerido20,
    BigDecimal precioSugerido30,
    BigDecimal precioSugerido50) {}
