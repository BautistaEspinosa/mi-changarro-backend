package com.baersolutions.mi_changarro_app.modules.metas.repository;

import com.baersolutions.mi_changarro_app.modules.metas.entity.Meta;
import com.baersolutions.mi_changarro_app.modules.metas.entity.MovimientoAhorro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para MovimientoAhorro.
 *
 * <p>Permite persistir y consultar los movimientos de ahorro automático
 * generados para una meta.
 *
 * @author Baer Solutions
 */
public interface MovimientoAhorroRepository
    extends JpaRepository<MovimientoAhorro, Long> {

  /**
   * Obtiene los movimientos de ahorro de una meta ordenados del más reciente
   * al más antiguo.
   *
   * @param meta meta asociada a los movimientos.
   * @return lista de movimientos encontrados.
   */
  List<MovimientoAhorro> findByMetaOrderByCreatedAtDesc(
      Meta meta
  );

}