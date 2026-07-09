package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

/**
 * Representa la respuesta principal del módulo Dashboard.
 *
 * <p>Consolida toda la información proveniente de los diferentes módulos del
 * sistema para presentar un resumen general del negocio.</p>
 *
 * @param resumenDinero resumen del dinero del negocio
 * @param origenDinero origen de los ingresos
 * @param resumenVentas resumen de ventas
 * @param resumenDeudas resumen de deudas
 * @param resumenMeta resumen de la meta activa
 * @param resumenInventario resumen del inventario
 *
 * @author Roman Bautista Espinosa
 */
public record DashboardResponseDTO(

    ResumenDineroResponseDTO resumenDinero,

    OrigenDineroResponseDTO origenDinero,

    ResumenVentasResponseDTO resumenVentas,

    ResumenDeudasResponseDTO resumenDeudas,

    ResumenMetaResponseDTO resumenMeta,

    ResumenInventarioResponseDTO resumenInventario

) {
}