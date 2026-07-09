package com.baersolutions.mi_changarro_app.modules.ventas.dto.request;

import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO que representa un producto propio dentro de una venta.
 *
 * @param productoId identificador del producto vendido.
 * @param cantidad   cantidad vendida.
 * @author Baer Solutions
 */
public record DetalleVentaRequestDTO(

    @NotNull(message = VentaMessages.PRODUCTO_ID_REQUERIDO)
    Long productoId,

    @NotNull(message = VentaMessages.CANTIDAD_REQUERIDA)
    @Min(value = 1, message = VentaMessages.CANTIDAD_POSITIVA)
    Integer cantidad

) {

}