package com.baersolutions.mi_changarro_app.modules.inventario.service;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;

import java.util.List;

/**
 * Define las operaciones del módulo de productos.
 */
public interface ProductoService {

  /**
   * Crea un nuevo producto en el sistema.
   */
  ProductoResponseDTO crearProducto(ProductoRequestDTO dto);

  /**
   * Obtiene un producto por su ID.
   */
  ProductoResponseDTO obtenerPorId(Long id);

  /**
   * Obtiene todos los productos activos.
   */
  List<ProductoResponseDTO> obtenerActivos();

  /**
   * Busca productos por nombre.
   */
  List<ProductoResponseDTO> buscarPorNombre(String nombre);

  /**
   * Desactiva un producto (soft delete).
   */
  ProductoResponseDTO desactivarProducto(Long id);

  /**
   * Activa un producto.
   */
  ProductoResponseDTO activarProducto(Long id);
}