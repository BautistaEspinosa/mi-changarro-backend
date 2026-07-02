package com.baersolutions.mi_changarro_app.modules.surtido.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
 * Entidad que representa una compra realizada para surtir el negocio.
 *
 * <p>Una compra funciona como el encabezado del proceso de surtido y puede
 * contener uno o varios productos mediante sus respectivos detalles.
 *
 * <p>La fecha de registro de la compra se obtiene automáticamente del campo
 * {@code createdAt} heredado de {@link BaseEntity}.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra extends BaseEntity {

  /**
   * Identificador único de la compra.
   */
  @jakarta.persistence.Id
  @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  /**
   * Monto total pagado por la compra.
   */
  @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
  private BigDecimal montoTotal;

  /**
   * Lista de productos incluidos en la compra.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "compra",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<DetalleCompra> detallesCompra = new ArrayList<>();
}