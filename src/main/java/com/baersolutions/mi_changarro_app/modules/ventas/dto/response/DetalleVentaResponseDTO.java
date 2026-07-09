package com.baersolutions.mi_changarro_app.modules.ventas.dto.response;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import java.math.BigDecimal;

/**
 * DTO que representa un producto vendido dentro de una venta.
 *
 * @param producto              información del producto vendido.
 * @param cantidad              cantidad vendida.
 * @param precioUnitario        precio congelado al momento de la venta.
 * @param costoUnitarioSnapshot costo congelado al momento de la venta.
 * @param subtotal              subtotal calculado del producto vendido.
 * @author Baer Solutions
 */
public record DetalleVentaResponseDTO(

    ProductoResponseDTO producto,
    Integer cantidad,
    BigDecimal precioUnitario,
    BigDecimal costoUnitarioSnapshot,
    BigDecimal subtotal

) {

}