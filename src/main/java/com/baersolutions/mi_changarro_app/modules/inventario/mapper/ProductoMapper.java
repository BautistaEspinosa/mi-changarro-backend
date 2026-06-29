package com.baersolutions.mi_changarro_app.modules.inventario.mapper;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;

/**
 * Mapper manual del módulo Producto.
 */
public final class ProductoMapper {

  private ProductoMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte RequestDTO a entidad.
   */
  public static Producto toEntity(ProductoRequestDTO dto) {

    return Producto.builder()
        .nombre(dto.nombre())
        .costoUnitarioActual(dto.costoUnitarioActual())
        .precioVenta(dto.precioVenta())
        .stockActual(dto.stockActual())
        .stockMinimo(dto.stockMinimo())
        .activo(true)
        .build();
  }

  /**
   * Convierte entidad a ResponseDTO.
   */
  public static ProductoResponseDTO toDTO(Producto entity) {

    return new ProductoResponseDTO(
        entity.getId(),
        entity.getNombre(),
        entity.getCostoUnitarioActual(),
        entity.getPrecioVenta(),
        entity.getStockActual(),
        entity.getStockMinimo(),
        entity.getActivo()
    );
  }

}