package com.baersolutions.mi_changarro_app.modules.medeben.mapper;

import com.baersolutions.mi_changarro_app.modules.medeben.dto.response.DeudaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;

/**
 * Mapper manual del módulo Me Deben.
 *
 * <p>Convierte entidades {@link Deuda} a DTOs de respuesta, exponiendo solo
 * la información necesaria para consultar deudas desde la API.
 *
 * @author Baer Solutions
 */
public final class DeudaMapper {

  /**
   * Constructor privado para evitar instanciación.
   */
  private DeudaMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte una entidad Deuda a su DTO de respuesta.
   *
   * @param entity deuda registrada.
   * @return DTO de respuesta de la deuda.
   */
  public static DeudaResponseDTO toDTO(final Deuda entity) {

    return new DeudaResponseDTO(
        entity.getId(),
        entity.getVenta().getId(),
        entity.getNombreCliente(),
        entity.getMontoPendiente(),
        entity.getFechaCompra(),
        entity.getFechaPago(),
        entity.getEstado()
    );
  }

}