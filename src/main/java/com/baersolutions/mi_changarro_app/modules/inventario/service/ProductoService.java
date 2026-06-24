package com.baersolutions.mi_changarro_app.modules.inventario.service;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoCreateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

  ProductoResponseDTO crearProducto(ProductoCreateRequestDTO dto);

  ProductoResponseDTO obtenerPorId(Long id);

  List<ProductoResponseDTO> listarActivos();

  ProductoResponseDTO actualizarProducto(Long id, ProductoUpdateRequestDTO dto);

  void desactivarProducto(Long id);

  void activarProducto(Long id);
}