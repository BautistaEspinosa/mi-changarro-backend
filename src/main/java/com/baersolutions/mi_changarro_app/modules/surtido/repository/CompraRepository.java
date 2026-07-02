package com.baersolutions.mi_changarro_app.modules.surtido.repository;

import com.baersolutions.mi_changarro_app.modules.surtido.entity.Compra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de las compras de surtido.
 *
 * <p>Proporciona las operaciones de acceso a datos para la entidad
 * {@link Compra}.
 *
 * @author Baer Solutions
 */
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

  /**
   * Obtiene el historial de compras ordenado de la más reciente a la más antigua.
   *
   * @return lista de compras ordenadas por fecha de creación descendente.
   */
  List<Compra> findAllByOrderByCreatedAtDesc();

}