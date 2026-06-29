package com.baersolutions.mi_changarro_app.modules.inventario.dto.response;

import java.math.BigDecimal;

/**
 * Response del producto para la API.
 */
public record ProductoResponseDTO(

    Long id,
    String nombre,
    BigDecimal costoUnitarioActual,
    BigDecimal precioVenta,
    Integer stockActual,
    Integer stockMinimo,
    Boolean activo

) {
}