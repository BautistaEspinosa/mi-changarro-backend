package com.baersolutions.mi_changarro_app.modules.medeben.dto.response;

import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO que representa la información de una deuda.
 *
 * <p>Expone la información necesaria para que el usuario pueda saber quién le
 * debe, cuánto le debe, desde cuándo y si la deuda ya fue pagada.
 *
 * @param id identificador de la deuda.
 * @param ventaId identificador de la venta que originó la deuda.
 * @param nombreCliente nombre del cliente que debe.
 * @param montoPendiente monto original pendiente de pago.
 * @param fechaCompra fecha original de la venta FIADO.
 * @param fechaPago fecha en la que se marcó como pagada.
 * @param estado estado actual de la deuda.
 *
 * @author Baer Solutions
 */
public record DeudaResponseDTO(

    Long id,
    Long ventaId,
    String nombreCliente,
    BigDecimal montoPendiente,
    LocalDateTime fechaCompra,
    LocalDateTime fechaPago,
    EstadoDeuda estado

) {
}