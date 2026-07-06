package com.baersolutions.mi_changarro_app.modules.ventas.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.EstadoVenta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.TipoVenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una venta del negocio.
 *
 * <p>Funciona como cabecera de la boleta única de venta. Puede contener
 * productos propios mediante {@link DetalleVenta} y, opcionalmente, un ingreso
 * Betterware mediante {@link VentaBetterware}.
 *
 * <p>La fecha oficial de la venta se obtiene del campo {@code createdAt}
 * heredado de {@link BaseEntity}.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta extends BaseEntity {

  /**
   * Identificador único de la venta.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Tipo de venta realizada.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_venta", nullable = false)
  private TipoVenta tipoVenta;

  /**
   * Estado calculado de la venta.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "estado_venta", nullable = false)
  private EstadoVenta estadoVenta;

  /**
   * Nombre del cliente cuando la venta es FIADO.
   */
  @Column(name = "nombre_cliente")
  private String nombreCliente;

  /**
   * Total final de la venta.
   */
  @Column(name = "total_venta", nullable = false, precision = 10, scale = 2)
  private BigDecimal totalVenta;

  /**
   * Productos propios incluidos en la venta.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "venta",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<DetalleVenta> detallesVenta = new ArrayList<>();

  /**
   * Ingreso Betterware asociado a la venta.
   */
  @OneToOne(
      mappedBy = "venta",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  private VentaBetterware ventaBetterware;
}