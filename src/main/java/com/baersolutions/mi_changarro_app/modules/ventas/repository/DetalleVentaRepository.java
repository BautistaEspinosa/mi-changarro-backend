package com.baersolutions.mi_changarro_app.modules.ventas.repository;

import com.baersolutions.mi_changarro_app.modules.ventas.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para DetalleVenta.
 *
 * <p>Permite persistir y consultar los productos asociados a una venta.
 *
 * @author Baer Solutions
 */
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}