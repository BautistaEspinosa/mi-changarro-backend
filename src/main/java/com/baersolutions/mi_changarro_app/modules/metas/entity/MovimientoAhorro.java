package com.baersolutions.mi_changarro_app.modules.metas.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import com.baersolutions.mi_changarro_app.modules.metas.enums.OrigenMovimientoAhorro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
 * Entidad que representa un movimiento de ahorro automático.
 *
 * <p>Cada movimiento registra una cantidad apartada para una meta activa cuando
 * entra dinero real al negocio, ya sea por una venta cobrada o por el pago de
 * una deuda.
 *
 * <p>La fecha oficial del movimiento se obtiene del campo {@code createdAt}
 * heredado de {@link BaseEntity}.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "movimiento_ahorro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoAhorro extends BaseEntity {

  /**
   * Identificador único del movimiento.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Meta a la que pertenece el movimiento de ahorro.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meta_id", nullable = false)
  private Meta meta;

  /**
   * Monto apartado para la meta.
   */
  @Column(name = "monto", nullable = false, precision = 10, scale = 2)
  private BigDecimal monto;

  /**
   * Origen del dinero que generó el ahorro automático.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "origen", nullable = false, length = 20)
  private OrigenMovimientoAhorro origen;

}