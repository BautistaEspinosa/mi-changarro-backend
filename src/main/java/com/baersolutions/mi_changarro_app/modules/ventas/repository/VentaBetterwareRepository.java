package com.baersolutions.mi_changarro_app.modules.ventas.repository;

import com.baersolutions.mi_changarro_app.modules.ventas.entity.VentaBetterware;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para VentaBetterware.
 *
 * <p>Permite persistir y consultar el ingreso Betterware asociado a una venta.
 *
 * @author Baer Solutions
 */
public interface VentaBetterwareRepository extends JpaRepository<VentaBetterware, Long> {

}