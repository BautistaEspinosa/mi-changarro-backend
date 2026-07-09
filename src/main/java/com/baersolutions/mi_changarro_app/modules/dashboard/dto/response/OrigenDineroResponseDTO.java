package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

import java.math.BigDecimal;

/**
 * Representa el origen del dinero mostrado en el Dashboard.
 *
 * <p>Separa los ingresos obtenidos por productos propios y por Betterware,
 * permitiendo al usuario identificar de dónde proviene el dinero del negocio.</p>
 *
 * @param productosPropios dinero generado por la venta de productos propios
 * @param betterware dinero generado por ventas Betterware
 *
 * @author Roman Bautista Espinosa
 */
public record OrigenDineroResponseDTO(

    BigDecimal productosPropios,

    BigDecimal betterware

) {
}