package com.baersolutions.mi_changarro_app.modules.ventas.dto.response;

import java.math.BigDecimal;

/**
 * DTO que representa el ingreso Betterware asociado a una venta.
 *
 * @param montoTotal monto total ingresado por Betterware.
 *
 * @author Baer Solutions
 */
public record VentaBetterwareResponseDTO(

    BigDecimal montoTotal

) {
}