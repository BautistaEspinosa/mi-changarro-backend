package com.baersolutions.mi_changarro_app.modules.metas.dto.request;

import com.baersolutions.mi_changarro_app.modules.metas.constants.MetaMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de entrada para crear una meta de ahorro.
 *
 * <p>Contiene únicamente la información que el usuario debe proporcionar:
 * nombre, costo objetivo y fecha límite. Los valores calculados como ahorro
 * requerido, ahorro acumulado y estado activo son determinados por el sistema.
 *
 * @param nombre nombre visible de la meta.
 * @param costoObjetivo costo total que se desea alcanzar.
 * @param fechaLimite fecha límite definida por el usuario.
 *
 * @author Baer Solutions
 */
public record MetaRequestDTO(

    @NotBlank(message = MetaMessages.NOMBRE_REQUERIDO)
    String nombre,

    @NotNull(message = MetaMessages.COSTO_OBJETIVO_REQUERIDO)
    @DecimalMin(
        value = "0.01",
        message = MetaMessages.COSTO_OBJETIVO_INVALIDO
    )
    BigDecimal costoObjetivo,

    @NotNull(message = MetaMessages.FECHA_LIMITE_REQUERIDA)
    @Future(message = MetaMessages.FECHA_LIMITE_INVALIDA)
    LocalDate fechaLimite

) {
}