package com.baersolutions.mi_changarro_app.modules.medeben.repository;

import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;
import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de acceso a datos para Deuda.
 *
 * <p>Permite persistir y consultar las deudas generadas automáticamente desde
 * ventas FIADO.</p>
 *
 * @author Baer Solutions
 */
public interface DeudaRepository extends JpaRepository<Deuda, Long> {

  /**
   * Obtiene las deudas por estado, ordenadas de la más reciente a la más antigua.
   *
   * @param estado estado de deuda a consultar
   * @return lista de deudas encontradas
   */
  List<Deuda> findByEstadoOrderByCreatedAtDesc(
      EstadoDeuda estado
  );

  /**
   * Obtiene todas las deudas ordenadas de la más reciente a la más antigua.
   *
   * @return lista de deudas registradas
   */
  List<Deuda> findAllByOrderByCreatedAtDesc();

  /**
   * Obtiene el monto total de las deudas pendientes.
   *
   * @param estado estado PENDIENTE
   * @return total pendiente por cobrar
   */
  @Query("""
      SELECT COALESCE(SUM(d.montoPendiente), 0)
      FROM Deuda d
      WHERE d.estado = :estado
      """)
  BigDecimal obtenerTotalDeudasPendientes(
      @Param("estado") EstadoDeuda estado
  );

  /**
   * Obtiene el monto total de las deudas pagadas.
   *
   * <p>Representa dinero que ya ingresó nuevamente al negocio.</p>
   *
   * @param estado estado PAGADA
   * @return total recuperado
   */
  @Query("""
      SELECT COALESCE(SUM(d.montoPendiente), 0)
      FROM Deuda d
      WHERE d.estado = :estado
      """)
  BigDecimal obtenerTotalDeudasPagadas(
      @Param("estado") EstadoDeuda estado
  );

}