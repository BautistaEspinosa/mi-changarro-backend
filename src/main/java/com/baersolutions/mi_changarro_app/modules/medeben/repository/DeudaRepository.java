package com.baersolutions.mi_changarro_app.modules.medeben.repository;

import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;
import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para Deuda.
 *
 * <p>Permite persistir y consultar las deudas generadas automáticamente desde
 * ventas FIADO.
 *
 * @author Baer Solutions
 */
public interface DeudaRepository extends JpaRepository<Deuda, Long> {

  /**
   * Obtiene las deudas por estado, ordenadas de la más reciente a la más antigua.
   *
   * @param estado estado de deuda a consultar.
   * @return lista de deudas encontradas.
   */
  List<Deuda> findByEstadoOrderByCreatedAtDesc(
      EstadoDeuda estado
  );

  /**
   * Obtiene todas las deudas ordenadas de la más reciente a la más antigua.
   *
   * @return lista de deudas registradas.
   */
  List<Deuda> findAllByOrderByCreatedAtDesc();

}