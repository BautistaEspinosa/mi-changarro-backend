package com.baersolutions.mi_changarro_app.modules.surtido.dto.request;

import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO utilizado para registrar una compra de surtido.
 *
 * <p>Una compra está conformada por uno o varios productos representados
 * mediante sus respectivos detalles.
 *
 * @param detallesCompra lista de productos incluidos en la compra.
 *
 * @author Baer Solutions
 */
public record CompraRequestDTO(

    @NotNull(message = SurtidoMessages.DETALLES_COMPRA_REQUERIDOS)
    @NotEmpty(message = SurtidoMessages.DETALLES_COMPRA_NO_VACIOS)
    List<@Valid DetalleCompraRequestDTO> detallesCompra

) {
}