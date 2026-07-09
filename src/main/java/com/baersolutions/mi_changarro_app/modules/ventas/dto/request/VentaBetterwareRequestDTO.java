package com.baersolutions.mi_changarro_app.modules.ventas.dto.request;

import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO que representa el ingreso Betterware dentro de una venta.
 *
 * @param montoTotal monto total ingresado por Betterware.
 * @author Baer Solutions
 */
public record VentaBetterwareRequestDTO(

    @NotNull(message = VentaMessages.MONTO_BETTERWARE_REQUERIDO)
    @DecimalMin(value = "0.01", message = VentaMessages.MONTO_BETTERWARE_POSITIVO)
    BigDecimal montoTotal

) {

}