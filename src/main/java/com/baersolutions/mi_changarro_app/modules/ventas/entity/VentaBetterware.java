package com.baersolutions.mi_changarro_app.modules.ventas.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa el ingreso Betterware asociado a una venta.
 *
 * <p>Betterware no tiene catálogo ni inventario dentro del MVP. Por esta razón,
 * este registro únicamente almacena el monto ingresado y su relación con la
 * venta correspondiente.
 *
 * <p>Este ingreso no afecta existencias de productos propios.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "venta_betterware")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaBetterware extends BaseEntity {

  /**
   * Identificador único del ingreso Betterware.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Venta a la que pertenece el ingreso Betterware.
   */
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venta_id", nullable = false, unique = true)
  private Venta venta;

  /**
   * Monto total ingresado por Betterware.
   */
  @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
  private BigDecimal montoTotal;
}