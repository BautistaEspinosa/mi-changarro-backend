package com.baersolutions.mi_changarro_app.modules.metas.dto.response;

import com.baersolutions.mi_changarro_app.modules.metas.enums.OrigenMovimientoAhorro;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para un movimiento de ahorro.
 *
 * <p>Representa una cantidad apartada automáticamente hacia una meta activa,
 * indicando el monto, origen del dinero y fecha del movimiento.
 *
 * @param id identificador del movimiento.
 * @param monto cantidad ahorrada.
 * @param origen origen del dinero ahorrado.
 * @param fechaMovimiento fecha en que se registró el ahorro.
 *
 * @author Baer Solutions
 */
public record MovimientoAhorroResponseDTO(

    Long id,
    BigDecimal monto,
    OrigenMovimientoAhorro origen,
    LocalDateTime fechaMovimiento

) {
}