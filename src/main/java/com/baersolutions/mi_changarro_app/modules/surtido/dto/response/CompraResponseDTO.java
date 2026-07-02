package com.baersolutions.mi_changarro_app.modules.surtido.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa la información de una compra registrada.
 *
 * <p>Cada instancia corresponde a una compra realizada para surtir el negocio,
 * incluyendo el monto total pagado, la fecha de registro y el detalle de los
 * productos adquiridos.
 *
 * <p>La fecha de la compra corresponde al campo {@code createdAt} heredado de
 * la entidad {@code BaseEntity} y se expone como {@code fechaCompra} para
 * mantener una nomenclatura orientada al negocio.
 *
 * @param id identificador de la compra.
 * @param montoTotal monto total pagado por la compra.
 * @param fechaCompra fecha y hora de registro de la compra.
 * @param detallesCompra productos incluidos en la compra.
 *
 * @author Baer Solutions
 */
public record CompraResponseDTO(

    Long id,

    BigDecimal montoTotal,

    LocalDateTime fechaCompra,

    List<DetalleCompraResponseDTO> detallesCompra

) {
}