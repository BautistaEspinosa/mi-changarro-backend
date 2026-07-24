package com.baersolutions.mi_changarro_app.modules.surtido.entity;

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
 * Entidad que representa el detalle de una compra.
 *
 * <p>Cada detalle corresponde a un producto incluido dentro de una compra. Al registrarse, el
 * sistema calcula el costo unitario con base en el costo total del producto y la cantidad
 * adquirida.
 *
 * <p>También conserva como snapshot histórico el nombre del producto y el precio de venta
 * seleccionado durante el registro de la compra.
 *
 * <p>El costo unitario calculado servirá para actualizar el costo unitario vigente del producto en
 * el inventario.
 *
 * <p>La fecha de creación y actualización del detalle es administrada automáticamente por {@link
 * BaseEntity}.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "detalle_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCompra extends BaseEntity {

  /** Identificador único del detalle de compra. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Compra a la que pertenece este detalle. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "compra_id", nullable = false)
  private Compra compra;

  /** Producto adquirido. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  /**
   * Nombre del producto al momento de registrar la compra.
   *
   * <p>Se almacena como snapshot histórico para conservar el nombre utilizado durante el surtido,
   * aunque posteriormente el producto sea renombrado.
   */
  @Column(name = "nombre_producto", nullable = false)
  private String nombreProducto;

  /**
   * Precio de venta seleccionado al momento de registrar la compra.
   *
   * <p>Se conserva como snapshot histórico y no cambia aunque posteriormente el precio vigente del
   * producto sea actualizado.
   */
  @Column(name = "precio_venta_seleccionado", nullable = false, precision = 10, scale = 2)
  private BigDecimal precioVentaSeleccionado;

  /** Cantidad adquirida del producto. */
  @Column(nullable = false)
  private Integer cantidad;

  /** Costo total pagado por este producto dentro de la compra. */
  @Column(name = "costo_total_producto", nullable = false, precision = 10, scale = 2)
  private BigDecimal costoTotalProducto;

  /**
   * Costo unitario calculado para esta compra.
   *
   * <p>Se obtiene dividiendo el costo total del producto entre la cantidad adquirida y representa
   * el costo unitario vigente de la última compra.
   */
  @Column(name = "costo_unitario_calculado", nullable = false, precision = 10, scale = 2)
  private BigDecimal costoUnitarioCalculado;
}
