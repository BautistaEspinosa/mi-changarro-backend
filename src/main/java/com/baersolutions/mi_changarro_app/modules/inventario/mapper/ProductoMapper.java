package com.baersolutions.mi_changarro_app.modules.inventario.mapper;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoCreateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;

import java.math.BigDecimal;

public class ProductoMapper {

  public static Producto toEntity(ProductoCreateRequestDTO dto) {
    return Producto.builder()
        .nombre(dto.nombre())
        .stockMinimo(dto.stockMinimo())
        .precioVentaActual(dto.precioVentaActual())
        .stockActual(0)
        .costoUnitarioActual(BigDecimal.ZERO)
        .activo(true)
        .build();
  }

  public static ProductoResponseDTO toDTO(Producto producto) {
    return new ProductoResponseDTO(
        producto.getId(),
        producto.getNombre(),
        producto.getStockActual(),
        producto.getStockMinimo(),
        producto.getPrecioVentaActual(),
        producto.getCostoUnitarioActual(),
        producto.getActivo(),
        producto.getCreatedAt(),
        producto.getUpdatedAt()
    );
  }
}