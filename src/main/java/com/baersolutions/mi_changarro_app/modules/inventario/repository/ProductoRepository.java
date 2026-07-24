package com.baersolutions.mi_changarro_app.modules.inventario.repository;

import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** Repositorio de acceso a datos para Producto. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  /**
   * Busca un producto por nombre exacto.
   *
   * @param nombre nombre del producto
   * @return producto encontrado
   */
  Optional<Producto> findByNombre(String nombre);

  /**
   * Busca un producto por nombre ignorando mayúsculas y minúsculas.
   *
   * @param nombre nombre del producto
   * @return producto encontrado
   */
  Optional<Producto> findByNombreIgnoreCase(String nombre);

  /**
   * Obtiene todos los productos activos.
   *
   * @return productos activos
   */
  List<Producto> findByActivoTrue();

  /**
   * Obtiene todos los productos inactivos.
   *
   * @return productos inactivos
   */
  List<Producto> findByActivoFalse();

  /**
   * Busca productos parcialmente por nombre.
   *
   * @param nombre texto que se desea buscar
   * @return productos encontrados
   */
  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  /**
   * Cuenta los productos activos.
   *
   * @return total de productos activos
   */
  long countByActivoTrue();

  /**
   * Verifica si existe un producto con el nombre indicado.
   *
   * @param nombre nombre que se desea validar
   * @return {@code true} si el producto existe
   */
  boolean existsByNombreIgnoreCase(String nombre);

  /**
   * Cuenta los productos activos con stock bajo usando el stock mínimo propio de cada producto.
   *
   * @return total de productos activos con stock bajo
   */
  @Query(
      """
      SELECT COUNT(p)
      FROM Producto p
      WHERE p.activo = true
      AND p.stockActual <= p.stockMinimo
      """)
  long countProductosConStockBajo();

  /**
   * Obtiene los productos activos con stock bajo usando el stock mínimo propio de cada producto.
   *
   * @return productos activos con stock bajo
   */
  @Query(
      """
      SELECT p
      FROM Producto p
      WHERE p.activo = true
      AND p.stockActual <= p.stockMinimo
      """)
  List<Producto> findProductosConStockBajo();

  /**
   * Verifica si existe otro producto con el mismo nombre.
   *
   * <p>Se utiliza durante la actualización para evitar nombres duplicados, excluyendo al producto
   * que se está modificando.
   *
   * @param nombre nombre que se desea validar
   * @param id identificador del producto que se está actualizando
   * @return {@code true} si existe otro producto con el mismo nombre
   */
  boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
}
