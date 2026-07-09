package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

import java.math.BigDecimal;

/**
 * Representa el resumen de las deudas mostrado en el Dashboard.
 *
 * <p>Este bloque permite visualizar el monto total que aún permanece pendiente
 * por cobrar derivado de las ventas realizadas a crédito.</p>
 *
 * @param totalDeudasPendientes monto total de las deudas pendientes
 *
 * @author Roman Bautista Espinosa
 */
public record ResumenDeudasResponseDTO(

    BigDecimal totalDeudasPendientes

) {
}