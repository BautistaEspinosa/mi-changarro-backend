package com.baersolutions.mi_changarro_app.modules.inventario.mapper;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;

/**
 * Mapper manual del módulo Producto.
 *
 * <p>Convierte entidades y DTOs del módulo Producto sin contener lógica
 * de negocio.</p>
 *
 * @author Baer Solutions
 */
public final class ProductoMapper {

  /**
   * Constructor privado para evitar instanciación.
   */
  private ProductoMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte un ProductoRequestDTO a entidad.
   *
   * @param dto información capturada del producto.
   * @return entidad Producto.
   */
  public static Producto toEntity(
      final ProductoRequestDTO dto
  ) {

    return Producto.builder()
        .nombre(dto.nombre())
        .costoUnitarioActual(dto.costoUnitarioActual())
        .precioVenta(dto.precioVenta())
        .stockActual(dto.stockActual())
        .stockMinimo(dto.stockMinimo())
        .activo(Boolean.TRUE)
        .build();
  }

  /**
   * Convierte una entidad Producto a ProductoResponseDTO.
   *
   * @param entity entidad Producto.
   * @return DTO de respuesta.
   */
  public static ProductoResponseDTO toDTO(
      final Producto entity
  ) {

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