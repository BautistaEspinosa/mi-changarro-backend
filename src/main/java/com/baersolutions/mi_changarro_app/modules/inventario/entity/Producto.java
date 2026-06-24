package com.baersolutions.mi_changarro_app.modules.inventario.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false)
  private Integer stockActual;

  @Column(nullable = false)
  private Integer stockMinimo;

  @Column(nullable = false)
  private BigDecimal precioVentaActual;

  @Column(nullable = false)
  private BigDecimal costoUnitarioActual;

  @Column(nullable = false)
  private Boolean activo;

}
