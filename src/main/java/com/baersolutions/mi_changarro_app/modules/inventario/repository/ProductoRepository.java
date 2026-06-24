package com.baersolutions.mi_changarro_app.modules.inventario.repository;

import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findByActivoTrue();

  boolean existsByNombreIgnoreCase(String nombre);
}
