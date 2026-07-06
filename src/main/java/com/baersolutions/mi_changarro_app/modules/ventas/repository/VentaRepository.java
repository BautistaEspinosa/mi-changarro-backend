package com.baersolutions.mi_changarro_app.modules.ventas.repository;

import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para Venta.
 *
 * <p>Permite persistir y consultar ventas registradas dentro del módulo.
 *
 * @author Baer Solutions
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {

  /**
   * Obtiene las ventas ordenadas de la más reciente a la más antigua.
   *
   * @return lista de ventas registradas.
   */
  List<Venta> findAllByOrderByCreatedAtDesc();
}