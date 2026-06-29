package com.baersolutions.mi_changarro_app.modules.inventario.service;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;

import java.util.List;

/**
 * Servicio de consulta de inventario.
 * Provee información del estado actual del stock.
 */
public interface InventarioService {

  /**
   * Obtiene todos los productos en inventario.
   */
  List<ProductoResponseDTO> obtenerInventario();

  /**
   * Obtiene productos con stock bajo.
   */
  List<ProductoResponseDTO> obtenerStockBajo();

  /**
   * Obtiene resumen simple del inventario.
   */
  long contarProductosActivos();
}