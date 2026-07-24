package com.baersolutions.mi_changarro_app.modules.inventario.service;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import java.util.List;

/** Define las operaciones del módulo de productos. */
public interface ProductoService {

  /** Crea un nuevo producto en el sistema. */
  ProductoResponseDTO crearProducto(ProductoRequestDTO dto);

  /** Obtiene un producto por su ID. */
  ProductoResponseDTO obtenerPorId(Long id);

  /** Obtiene todos los productos activos. */
  List<ProductoResponseDTO> obtenerActivos();

  /** Obtiene todos los productos inactivos. */
  List<ProductoResponseDTO> obtenerInactivos();

  /** Busca productos por nombre. */
  List<ProductoResponseDTO> buscarPorNombre(String nombre);

  /** Desactiva un producto (soft delete). */
  ProductoResponseDTO desactivarProducto(Long id);

  /** Activa un producto. */
  ProductoResponseDTO activarProducto(Long id);

  /**
   * Actualiza la información editable de un producto.
   *
   * <p>Solo permite modificar el nombre y el stock mínimo. El costo unitario, precio de venta y
   * stock actual son administrados por el módulo Surtido.
   *
   * @param id identificador del producto.
   * @param dto información editable del producto.
   * @return producto actualizado.
   */
  ProductoResponseDTO actualizarProducto(Long id, ProductoUpdateRequestDTO dto);
}
