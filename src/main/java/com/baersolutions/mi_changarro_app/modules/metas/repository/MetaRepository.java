package com.baersolutions.mi_changarro_app.modules.metas.repository;

import com.baersolutions.mi_changarro_app.modules.metas.entity.Meta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para Meta.
 *
 * <p>Permite persistir metas, consultar la meta activa y obtener el historial
 * de metas registradas.
 *
 * @author Baer Solutions
 */
public interface MetaRepository extends JpaRepository<Meta, Long> {

  /**
   * Obtiene la meta activa del sistema.
   *
   * @param activa estado activo de la meta.
   * @return meta activa encontrada.
   */
  Optional<Meta> findByActiva(
      Boolean activa
  );

  /**
   * Obtiene todas las metas ordenadas de la más reciente a la más antigua.
   *
   * @return lista de metas registradas.
   */
  List<Meta> findAllByOrderByCreatedAtDesc();

}