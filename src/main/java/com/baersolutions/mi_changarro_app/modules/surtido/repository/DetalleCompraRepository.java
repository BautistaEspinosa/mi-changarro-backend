package com.baersolutions.mi_changarro_app.modules.surtido.repository;

import com.baersolutions.mi_changarro_app.modules.surtido.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de los detalles de compra.
 *
 * <p>Proporciona las operaciones de acceso a datos para la entidad
 * {@link DetalleCompra}.
 *
 * <p>La mayoría de las operaciones de persistencia son realizadas
 * automáticamente mediante la relación en cascada desde la entidad
 * {@code Compra}. Este repositorio queda disponible para futuras consultas
 * específicas del módulo.
 *
 * @author Baer Solutions
 */
@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
}