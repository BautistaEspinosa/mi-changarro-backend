package com.baersolutions.mi_changarro_app.modules.ventas.dto.request;

import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaMessages;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.TipoVenta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO utilizado para registrar una venta.
 *
 * <p>Una venta puede contener productos propios, un ingreso Betterware o ambos
 * dentro de una misma boleta.
 *
 * @param tipoVenta       tipo de venta: CONTADO o FIADO.
 * @param nombreCliente   nombre del cliente cuando la venta es FIADO.
 * @param detallesVenta   productos propios incluidos en la venta.
 * @param ventaBetterware ingreso Betterware incluido en la venta.
 * @author Baer Solutions
 */
public record VentaRequestDTO(

    @NotNull(message = VentaMessages.TIPO_VENTA_REQUERIDO)
    TipoVenta tipoVenta,

    String nombreCliente,

    List<@Valid DetalleVentaRequestDTO> detallesVenta,

    @Valid
    VentaBetterwareRequestDTO ventaBetterware

) {

}