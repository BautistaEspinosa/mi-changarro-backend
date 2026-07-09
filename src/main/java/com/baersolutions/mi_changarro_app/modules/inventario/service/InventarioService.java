package com.baersolutions.mi_changarro_app.modules.inventario.service;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import java.util.List;

/**
 * Servicio de consulta de inventario.
 *
 * <p>Provee información del estado actual del stock.</p>
 */
public interface InventarioService {

  /**
   * Obtiene todos los productos en inventario.
   *
   * @return lista de productos activos
   */
  List<ProductoResponseDTO> obtenerInventario();

  /**
   * Obtiene productos con stock bajo.
   *
   * @return lista de productos con stock bajo
   */
  List<ProductoResponseDTO> obtenerStockBajo();

  /**
   * Obtiene el total de productos activos.
   *
   * @return total de productos activos
   */
  long obtenerTotalProductosActivos();

  /**
   * Obtiene el total de productos activos con stock bajo.
   *
   * @return total de productos con stock bajo
   */
  long obtenerTotalProductosConStockBajo();

}