package com.baersolutions.mi_changarro_app.modules.medeben.entity;

import com.baersolutions.mi_changarro_app.common.entity.BaseEntity;
import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una deuda generada por una venta FIADO.
 *
 * <p>La deuda conserva la información original de la venta: cliente, monto y
 * fecha de origen. Marcar una deuda como pagada no modifica la venta histórica,
 * únicamente actualiza el estado de la deuda.
 *
 * <p>La fecha oficial de creación de la deuda se obtiene del campo
 * {@code createdAt} heredado de {@link BaseEntity}. La fecha de compra se
 * conserva como snapshot desde la venta original.
 *
 * @author Baer Solutions
 */
@Entity
@Table(name = "deuda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deuda extends BaseEntity {

  /**
   * Identificador único de la deuda.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Venta FIADO que originó la deuda.
   */
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venta_id", nullable = false, unique = true)
  private Venta venta;

  /**
   * Nombre del cliente que debe.
   */
  @Column(name = "nombre_cliente", nullable = false, length = 100)
  private String nombreCliente;

  /**
   * Monto original pendiente de pago.
   */
  @Column(name = "monto_pendiente", nullable = false, precision = 10, scale = 2)
  private BigDecimal montoPendiente;

  /**
   * Fecha original de la venta FIADO.
   */
  @Column(name = "fecha_compra", nullable = false)
  private LocalDateTime fechaCompra;

  /**
   * Fecha en la que la deuda fue pagada.
   */
  @Column(name = "fecha_pago")
  private LocalDateTime fechaPago;

  /**
   * Estado actual de la deuda.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false, length = 10)
  private EstadoDeuda estado;

}