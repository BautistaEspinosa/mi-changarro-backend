package com.baersolutions.mi_changarro_app.modules.surtido.dto.request;

import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO utilizado para calcular los precios sugeridos de un producto antes de registrar una compra.
 *
 * <p>El cálculo se realiza utilizando la cantidad adquirida y el costo total pagado por el
 * producto. Esta operación no modifica información en la base de datos.
 *
 * @param cantidad cantidad adquirida del producto.
 * @param costoTotalProducto costo total pagado por el producto.
 * @author Baer Solutions
 */
public record PrecioSugeridoRequestDTO(
    @NotNull(message = SurtidoMessages.CANTIDAD_REQUERIDA)
        @Min(value = 1, message = SurtidoMessages.CANTIDAD_POSITIVA)
        Integer cantidad,
    @NotNull(message = SurtidoMessages.COSTO_TOTAL_PRODUCTO_REQUERIDO)
        @DecimalMin(value = "0.01", message = SurtidoMessages.COSTO_TOTAL_POSITIVO)
        BigDecimal costoTotalProducto) {}
