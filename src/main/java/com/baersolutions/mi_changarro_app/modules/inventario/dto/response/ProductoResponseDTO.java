package com.baersolutions.mi_changarro_app.modules.inventario.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductoResponseDTO(
    Long id,
    String nombre,
    Integer stockActual,
    Integer stockMinimo,
    BigDecimal precioVentaActual,
    BigDecimal costoUnitarioActual,
    Boolean activo,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
