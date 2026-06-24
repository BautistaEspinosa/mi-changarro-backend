package com.baersolutions.mi_changarro_app.modules.inventario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductoUpdateRequestDTO(
    @NotBlank
    String nombre,

    @NotNull
    @PositiveOrZero
    Integer stockMinimo,

    @NotNull
    @Positive
    BigDecimal precioVentaActual
) {

}
