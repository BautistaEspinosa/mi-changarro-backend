package com.baersolutions.mi_changarro_app.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Entidad base que proporciona auditoría automática para todas las entidades persistentes del
 * sistema.
 *
 * <p>Las entidades que hereden de esta clase obtendrán automáticamente los
 * campos de fecha de creación y fecha de última actualización, administrados mediante los eventos
 * del ciclo de vida de JPA.</p>
 *
 * @author Baer Solutions
 */
@Getter
@MappedSuperclass
public abstract class BaseEntity {

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  /**
   * Se ejecuta automáticamente antes de persistir una entidad.
   */
  @PrePersist
  protected void onCreate() {

    LocalDateTime now = LocalDateTime.now();

    this.createdAt = now;
    this.updatedAt = now;
  }

  /**
   * Se ejecuta automáticamente antes de actualizar una entidad.
   */
  @PreUpdate
  protected void onUpdate() {

    this.updatedAt = LocalDateTime.now();
  }

}