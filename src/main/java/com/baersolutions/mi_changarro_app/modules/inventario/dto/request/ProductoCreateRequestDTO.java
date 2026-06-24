package com.baersolutions.mi_changarro_app.modules.inventario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductoCreateRequestDTO(

    @NotBlank
    String nombre,

    @NotNull
    @PositiveOrZero
    Integer stockMinimo,

    @NotNull
        @PositiveOrZero
    BigDecimal precioVentaActual
) {
}
