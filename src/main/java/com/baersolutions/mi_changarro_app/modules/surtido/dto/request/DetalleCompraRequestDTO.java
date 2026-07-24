package com.baersolutions.mi_changarro_app.modules.surtido.dto.request;

import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import com.baersolutions.mi_changarro_app.modules.surtido.enums.TipoPrecio;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO que representa un producto dentro de una compra.
 *
 * <p>Cada instancia corresponde a un detalle de la compra e incluye la información necesaria para
 * registrar un producto adquirido y definir su precio de venta.
 *
 * <p>Cuando se selecciona una opción sugerida, el backend calcula el precio utilizando los márgenes
 * definidos en {@code BusinessConstants}. Cuando se selecciona la opción personalizada, se utiliza
 * el valor recibido en {@code precioPersonalizado}.
 *
 * @param nombreProducto nombre del producto.
 * @param cantidad cantidad adquirida.
 * @param costoTotalProducto costo total pagado por el producto.
 * @param tipoPrecio opción seleccionada para definir el precio de venta.
 * @param precioPersonalizado precio capturado manualmente cuando se selecciona la opción
 *     personalizada.
 * @author Baer Solutions
 */
public record DetalleCompraRequestDTO(
    @NotBlank(message = SurtidoMessages.PRODUCTO_REQUERIDO) String nombreProducto,
    @NotNull(message = SurtidoMessages.CANTIDAD_REQUERIDA)
        @Min(value = 1, message = SurtidoMessages.CANTIDAD_POSITIVA)
        Integer cantidad,
    @NotNull(message = SurtidoMessages.COSTO_TOTAL_PRODUCTO_REQUERIDO)
        @DecimalMin(value = "0.01", message = SurtidoMessages.COSTO_TOTAL_POSITIVO)
        BigDecimal costoTotalProducto,
    @NotNull(message = SurtidoMessages.TIPO_PRECIO_REQUERIDO) TipoPrecio tipoPrecio,
    @DecimalMin(value = "0.01", message = SurtidoMessages.PRECIO_PERSONALIZADO_POSITIVO)
        BigDecimal precioPersonalizado) {}
