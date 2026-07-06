package com.baersolutions.mi_changarro_app.modules.ventas.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un producto vendido dentro de una venta.
 *
 * <p>Cada detalle guarda una fotografía del momento de la venta:
 * cantidad, precio unitario, costo unitario y subtotal.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVenta extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venta_id", nullable = false)
  private Venta venta;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  @Column(nullable = false)
  private Integer cantidad;

  @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
  private BigDecimal precioUnitario;

  @Column(name = "costo_unitario_snapshot", nullable = false, precision = 10, scale = 2)
  private BigDecimal costoUnitarioSnapshot;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal subtotal;
}