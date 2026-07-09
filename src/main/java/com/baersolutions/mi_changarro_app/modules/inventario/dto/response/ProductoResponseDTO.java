package com.baersolutions.mi_changarro_app.modules.inventario.dto.response;

import java.math.BigDecimal;

/**
 * DTO de respuesta para un producto.
 *
 * <p>Representa la información visible de un producto registrado en el
 * catálogo e inventario del negocio.</p>
 *
 * @param id identificador del producto
 * @param nombre nombre del producto
 * @param costoUnitarioActual costo unitario actual
 * @param precioVenta precio de venta actual
 * @param stockActual existencia actual
 * @param stockMinimo stock mínimo para alerta
 * @param activo indica si el producto está activo
 *
 * @author Baer Solutions
 */
public record ProductoResponseDTO(

    Long id,
    String nombre,
    BigDecimal costoUnitarioActual,
    BigDecimal precioVenta,
    Integer stockActual,
    Integer stockMinimo,
    Boolean activo

) {
}