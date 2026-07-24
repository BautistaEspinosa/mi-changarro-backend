package com.baersolutions.mi_changarro_app.modules.surtido.dto.response;

import java.math.BigDecimal;

/**
 * DTO que representa el detalle de una compra registrada.
 *
 * <p>Cada instancia corresponde a un producto incluido dentro de una compra, * mostrando la
 * información histórica registrada durante el surtido, * incluyendo el nombre del producto, la
 * cantidad adquirida, el costo * total pagado, el costo unitario calculado, el precio de venta *
 * seleccionado y las sugerencias de precio generadas por el sistema.
 *
 * <p>Las sugerencias de precio se calculan automáticamente utilizando los multiplicadores definidos
 * en {@code BusinessConstants} y no se almacenan en la base de datos.
 *
 * @param nombreProducto nombre del producto al momento del surtido.
 * @param cantidad cantidad adquirida.
 * @param costoTotalProducto costo total pagado por el producto.
 * @param costoUnitarioCalculado costo unitario obtenido de la compra.
 * @param precioVentaSeleccionado precio de venta elegido durante el surtido.
 * @param precioSugerido20 precio sugerido con un margen del 20 %.
 * @param precioSugerido30 precio sugerido con un margen del 30 %.
 * @param precioSugerido50 precio sugerido con un margen del 50 %.
 * @author Baer Solutions
 */
public record DetalleCompraResponseDTO(
    String nombreProducto,
    Integer cantidad,
    BigDecimal costoTotalProducto,
    BigDecimal costoUnitarioCalculado,
    BigDecimal precioVentaSeleccionado,
    BigDecimal precioSugerido20,
    BigDecimal precioSugerido30,
    BigDecimal precioSugerido50) {}
