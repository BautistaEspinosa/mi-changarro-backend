package com.baersolutions.mi_changarro_app.modules.metas.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una meta de ahorro del negocio.
 *
 * <p>Permite registrar el objetivo que el usuario quiere alcanzar, el costo
 * total de la meta, la fecha límite y el avance acumulado mediante ahorro
 * automático.
 *
 * <p>Solo puede existir una meta activa a la vez. La fecha oficial de creación
 * de la meta se obtiene del campo {@code createdAt} heredado de
 * {@link BaseEntity}.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "meta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta extends BaseEntity {

  /**
   * Identificador único de la meta.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre visible de la meta.
   */
  @Column(nullable = false, length = 100)
  private String nombre;

  /**
   * Costo total que se desea alcanzar.
   */
  @Column(name = "costo_objetivo", nullable = false, precision = 10, scale = 2)
  private BigDecimal costoObjetivo;

  /**
   * Fecha límite definida por el usuario.
   */
  @Column(name = "fecha_limite", nullable = false)
  private LocalDate fechaLimite;

  /**
   * Monto que el sistema intentará apartar automáticamente en cada ingreso real.
   */
  @Column(name = "ahorro_requerido", nullable = false, precision = 10, scale = 2)
  private BigDecimal ahorroRequerido;

  /**
   * Ahorro acumulado hasta el momento.
   */
  @Column(name = "ahorro_acumulado", nullable = false, precision = 10, scale = 2)
  private BigDecimal ahorroAcumulado;

  /**
   * Indica si la meta se encuentra activa.
   */
  @Column(nullable = false)
  private Boolean activa;

  /**
   * Movimientos de ahorro asociados a la meta.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "meta",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<MovimientoAhorro> movimientosAhorro = new ArrayList<>();

}