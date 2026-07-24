package com.baersolutions.mi_changarro_app.modules.inventario.dto.request;

import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de entrada para actualizar la información editable de un producto.
 *
 * <p>La actualización manual de Producto únicamente permite modificar el nombre y el stock mínimo.
 * El costo unitario, precio de venta y stock actual son administrados mediante el módulo Surtido.
 *
 * @param nombre nombre del producto
 * @param stockMinimo stock mínimo para generar una alerta
 * @author Baer Solutions
 */
public record ProductoUpdateRequestDTO(
    @NotBlank(message = ProductoMessages.NOMBRE_REQUERIDO) String nombre,
    @NotNull(message = ProductoMessages.STOCK_MINIMO_REQUERIDO)
        @Min(value = 0, message = ProductoMessages.STOCK_MINIMO_REQUERIDO)
        Integer stockMinimo) {}
