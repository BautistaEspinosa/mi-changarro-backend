package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

/**
 * Representa el resumen del inventario mostrado en el Dashboard.
 *
 * <p>Contiene los indicadores generales del estado actual del inventario
 * utilizados por la pantalla principal.</p>
 *
 * @param productosConStockBajo cantidad de productos con stock bajo
 * @param totalProductosActivos cantidad total de productos activos
 *
 * @author Roman Bautista Espinosa
 */
public record ResumenInventarioResponseDTO(

    long productosConStockBajo,

    long totalProductosActivos

) {
}