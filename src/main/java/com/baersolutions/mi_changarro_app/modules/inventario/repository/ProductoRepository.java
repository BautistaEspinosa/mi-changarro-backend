package com.baersolutions.mi_changarro_app.modules.inventario.repository;

import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para Producto.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  /**
   * Busca un producto por nombre exacto.
   */
  Optional<Producto> findByNombre(String nombre);

  /**
   * Obtiene todos los productos activos.
   */
  List<Producto> findByActivoTrue();

  /**
   * Búsqueda parcial por nombre (útil para inventario).
   */
  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  /**
   * Conteo de productos activos.
   */
  long countByActivoTrue();

  /**
   * Verifica existencia por nombre (optimizado para validaciones).
   */
  boolean existsByNombreIgnoreCase(String nombre);

  List<Producto> findByActivoTrueAndStockActualLessThanEqual(Integer stockMinimo);
}