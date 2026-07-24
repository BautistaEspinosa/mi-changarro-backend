package com.baersolutions.mi_changarro_app.modules.surtido.mapper;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.DetalleCompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.Compra;
import com.baersolutions.mi_changarro_app.modules.surtido.entity.DetalleCompra;
import com.baersolutions.mi_changarro_app.modules.surtido.util.SurtidoCalculator;
import java.math.BigDecimal;
import java.util.List;

/**
 * Mapper manual del módulo Surtido.
 *
 * <p>Convierte las entidades del módulo Surtido en DTOs de respuesta, incorporando la información
 * calculada que únicamente forma parte de la respuesta de la API, como las sugerencias de precio de
 * venta.
 *
 * @author Baer Solutions
 */
public final class SurtidoMapper {

  /** Constructor privado para evitar instanciación. */
  private SurtidoMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte una entidad Compra a su DTO de respuesta.
   *
   * @param entity compra registrada.
   * @return DTO de respuesta de la compra.
   */
  public static CompraResponseDTO toDTO(final Compra entity) {

    List<DetalleCompraResponseDTO> detallesCompra =
        entity.getDetallesCompra().stream().map(SurtidoMapper::toDTO).toList();

    return new CompraResponseDTO(
        entity.getId(), entity.getMontoTotal(), entity.getCreatedAt(), detallesCompra);
  }

  /**
   * Convierte una entidad DetalleCompra a su DTO de respuesta.
   *
   * @param entity detalle de compra registrado.
   * @return DTO del detalle de compra.
   */
  public static DetalleCompraResponseDTO toDTO(final DetalleCompra entity) {

    BigDecimal costoUnitario = entity.getCostoUnitarioCalculado();

    return new DetalleCompraResponseDTO(
        entity.getNombreProducto(),
        entity.getCantidad(),
        entity.getCostoTotalProducto(),
        costoUnitario,
        entity.getPrecioVentaSeleccionado(),
        SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_20),
        SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_30),
        SurtidoCalculator.calcularPrecioSugerido(costoUnitario, BusinessConstants.MARGEN_50));
  }
}
