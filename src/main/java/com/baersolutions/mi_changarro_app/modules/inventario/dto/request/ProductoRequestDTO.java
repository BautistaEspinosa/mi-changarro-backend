package com.baersolutions.mi_changarro_app.modules.inventario.dto.request;

import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO de entrada para crear o actualizar un producto.
 *
 * <p>Contiene únicamente la información que el usuario debe proporcionar
 * para registrar o modificar un producto del catálogo.</p>
 *
 * @param nombre nombre del producto
 * @param costoUnitarioActual costo unitario actual del producto
 * @param precioVenta precio de venta del producto
 * @param stockActual existencia actual del producto
 * @param stockMinimo stock mínimo para alerta
 *
 * @author Baer Solutions
 */

public record ProductoRequestDTO(

    @NotBlank(message = ProductoMessages.NOMBRE_REQUERIDO)
    String nombre,

    @NotNull(message = ProductoMessages.COSTO_REQUERIDO)
    @DecimalMin(value = "0.0", message = ProductoMessages.COSTO_REQUERIDO)
    BigDecimal costoUnitarioActual,

    @NotNull(message = ProductoMessages.PRECIO_REQUERIDO)
    @DecimalMin(value = "0.0", message = ProductoMessages.PRECIO_REQUERIDO)
    BigDecimal precioVenta,

    @NotNull(message = ProductoMessages.STOCK_REQUERIDO)
    @Min(value = 0, message = ProductoMessages.STOCK_REQUERIDO)
    Integer stockActual,

    @NotNull(message = ProductoMessages.STOCK_MINIMO_REQUERIDO)
    @Min(value = 0, message = ProductoMessages.STOCK_MINIMO_REQUERIDO)
    Integer stockMinimo

) {
}