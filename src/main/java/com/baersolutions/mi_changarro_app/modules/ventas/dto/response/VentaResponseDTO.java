package com.baersolutions.mi_changarro_app.modules.ventas.dto.response;

import com.baersolutions.mi_changarro_app.modules.ventas.enums.EstadoVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.TipoVenta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa la información de una venta registrada.
 *
 * @param id identificador de la venta.
 * @param tipoVenta tipo de venta registrada.
 * @param estadoVenta estado calculado de la venta.
 * @param nombreCliente nombre del cliente cuando la venta es FIADO.
 * @param totalVenta total final de la venta.
 * @param fechaVenta fecha de registro obtenida desde createdAt.
 * @param detallesVenta productos propios vendidos.
 * @param ventaBetterware ingreso Betterware asociado.
 *
 * @author Baer Solutions
 */
public record VentaResponseDTO(

    Long id,
    TipoVenta tipoVenta,
    EstadoVenta estadoVenta,
    String nombreCliente,
    BigDecimal totalVenta,
    LocalDateTime fechaVenta,
    List<DetalleVentaResponseDTO> detallesVenta,
    VentaBetterwareResponseDTO ventaBetterware

) {
}