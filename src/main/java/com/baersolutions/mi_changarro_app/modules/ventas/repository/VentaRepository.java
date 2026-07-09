package com.baersolutions.mi_changarro_app.modules.ventas.repository;

import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.EstadoVenta;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de acceso a datos para Venta.
 *
 * <p>Permite persistir y consultar ventas registradas dentro del módulo.</p>
 *
 * @author Baer Solutions
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {

  /**
   * Obtiene las ventas ordenadas de la más reciente a la más antigua.
   *
   * @return lista de ventas registradas
   */
  List<Venta> findAllByOrderByCreatedAtDesc();

  /**
   * Obtiene el total de ventas cobradas.
   *
   * @param estado estado COBRADO
   * @return total de ventas cobradas
   */
  @Query("""
      SELECT COALESCE(SUM(v.totalVenta), 0)
      FROM Venta v
      WHERE v.estadoVenta = :estado
      """)
  BigDecimal obtenerTotalVentasCobradas(
      @Param("estado") EstadoVenta estado
  );

  /**
   * Obtiene el total de ventas pendientes.
   *
   * @param estado estado PENDIENTE
   * @return total pendiente por cobrar
   */
  @Query("""
      SELECT COALESCE(SUM(v.totalVenta), 0)
      FROM Venta v
      WHERE v.estadoVenta = :estado
      """)
  BigDecimal obtenerTotalVentasPendientes(
      @Param("estado") EstadoVenta estado
  );

  /**
   * Obtiene el total cobrado por productos propios.
   *
   * @param estado estado COBRADO
   * @return total cobrado por productos propios
   */
  @Query("""
      SELECT COALESCE(SUM(d.subtotal), 0)
      FROM DetalleVenta d
      WHERE d.venta.estadoVenta = :estado
      """)
  BigDecimal obtenerTotalProductosPropiosCobrados(
      @Param("estado") EstadoVenta estado
  );

  /**
   * Obtiene el total cobrado por Betterware.
   *
   * @param estado estado COBRADO
   * @return total cobrado por Betterware
   */
  @Query("""
      SELECT COALESCE(SUM(b.montoTotal), 0)
      FROM VentaBetterware b
      WHERE b.venta.estadoVenta = :estado
      """)
  BigDecimal obtenerTotalBetterwareCobrado(
      @Param("estado") EstadoVenta estado
  );

  /**
   * Obtiene el dinero necesario para volver a comprar los productos vendidos.
   *
   * <p>El cálculo utiliza el costo unitario congelado al momento de la venta,
   * respetando la inmutabilidad del historial.</p>
   *
   * @param estado estado COBRADO
   * @return total para volver a comprar
   */
  @Query("""
      SELECT COALESCE(SUM(d.costoUnitarioSnapshot * d.cantidad), 0)
      FROM DetalleVenta d
      WHERE d.venta.estadoVenta = :estado
      """)
  BigDecimal obtenerTotalParaVolverAComprar(
      @Param("estado") EstadoVenta estado
  );
}