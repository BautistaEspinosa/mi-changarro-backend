package com.baersolutions.mi_changarro_app.modules.ventas.mapper;

import com.baersolutions.mi_changarro_app.modules.inventario.mapper.ProductoMapper;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.DetalleVentaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaBetterwareResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.DetalleVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.VentaBetterware;
import java.util.List;

/**
 * Mapper manual del módulo Ventas.
 *
 * <p>Convierte las entidades del módulo Ventas en DTOs de respuesta,
 * respetando la estructura Cabecera → Detalle y exponiendo la fecha de venta
 * desde el campo {@code createdAt} heredado de la entidad base.
 *
 * @author Baer Solutions
 */
public final class VentaMapper {

  /**
   * Constructor privado para evitar instanciación.
   */
  private VentaMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte una entidad Venta a su DTO de respuesta.
   *
   * @param entity venta registrada.
   * @return DTO de respuesta de la venta.
   */
  public static VentaResponseDTO toDTO(final Venta entity) {

    List<DetalleVentaResponseDTO> detallesVenta = entity.getDetallesVenta()
        .stream()
        .map(VentaMapper::toDTO)
        .toList();

    return new VentaResponseDTO(
        entity.getId(),
        entity.getTipoVenta(),
        entity.getEstadoVenta(),
        entity.getNombreCliente(),
        entity.getTotalVenta(),
        entity.getCreatedAt(),
        detallesVenta,
        toDTO(entity.getVentaBetterware())
    );
  }

  /**
   * Convierte una entidad DetalleVenta a su DTO de respuesta.
   *
   * @param entity detalle de venta registrado.
   * @return DTO de respuesta del detalle.
   */
  public static DetalleVentaResponseDTO toDTO(final DetalleVenta entity) {

    return new DetalleVentaResponseDTO(
        ProductoMapper.toDTO(entity.getProducto()),
        entity.getCantidad(),
        entity.getPrecioUnitario(),
        entity.getCostoUnitarioSnapshot(),
        entity.getSubtotal()
    );
  }

  /**
   * Convierte una entidad VentaBetterware a su DTO de respuesta.
   *
   * @param entity ingreso Betterware registrado.
   * @return DTO de respuesta del ingreso Betterware.
   */
  public static VentaBetterwareResponseDTO toDTO(final VentaBetterware entity) {

    if (entity == null) {
      return null;
    }

    return new VentaBetterwareResponseDTO(
        entity.getMontoTotal()
    );
  }
}