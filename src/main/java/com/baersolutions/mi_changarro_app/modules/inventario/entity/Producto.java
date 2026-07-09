package com.baersolutions.mi_changarro_app.modules.inventario.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un producto del inventario.
 */
@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "nombre",
      nullable = false,
      length = 100
  )
  private String nombre;

  @Column(name ="costo_unitario_actual",nullable = false,precision = 10, scale = 2)
  private BigDecimal costoUnitarioActual;

  @Column(name ="precio_venta",nullable = false,precision = 10, scale = 2)
  private BigDecimal precioVenta;

  @Column(name = "stock_actual", nullable = false)
  private Integer stockActual;

  @Column(name = "stock_minimo", nullable = false)
  private Integer stockMinimo;

  @Builder.Default
  @Column(
      name = "activo",
      nullable = false
  )
  private Boolean activo = Boolean.TRUE;

}