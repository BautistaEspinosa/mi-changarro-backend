package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

import java.math.BigDecimal;

/**
 * Representa el resumen de las ventas mostrado en el Dashboard.
 *
 * <p>Permite visualizar el dinero proveniente de las ventas ya cobradas y el
 * dinero que aún se encuentra pendiente por cobrar.</p>
 *
 * @param totalVentasCobradas monto total de las ventas cobradas
 * @param totalPendientePorCobrar monto total de las ventas pendientes por cobrar
 *
 * @author Roman Bautista Espinosa
 */
public record ResumenVentasResponseDTO(

    BigDecimal totalVentasCobradas,

    BigDecimal totalPendientePorCobrar

) {
}