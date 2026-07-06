package com.baersolutions.mi_changarro_app.modules.metas.mapper;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MovimientoAhorroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.entity.Meta;
import com.baersolutions.mi_changarro_app.modules.metas.entity.MovimientoAhorro;
import java.math.BigDecimal;

/**
 * Mapper manual del módulo Metas.
 *
 * <p>Convierte entidades del módulo a sus DTOs de respuesta, incluyendo valores
 * calculados que no se almacenan en base de datos, como el porcentaje de avance
 * de una meta.
 *
 * @author Baer Solutions
 */
public final class MetaMapper {

  private static final BigDecimal CIEN = BigDecimal.valueOf(100);

  private MetaMapper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Convierte una entidad Meta a MetaResponseDTO.
   *
   * @param meta entidad de meta.
   * @return DTO de respuesta con información de la meta.
   */
  public static MetaResponseDTO toDTO(
      final Meta meta
  ) {

    return new MetaResponseDTO(
        meta.getId(),
        meta.getNombre(),
        meta.getCostoObjetivo(),
        meta.getAhorroRequerido(),
        meta.getAhorroAcumulado(),
        calcularPorcentajeAvance(meta),
        meta.getFechaLimite(),
        meta.getCreatedAt(),
        meta.getActiva()
    );
  }

  /**
   * Convierte una entidad MovimientoAhorro a MovimientoAhorroResponseDTO.
   *
   * @param movimiento entidad de movimiento de ahorro.
   * @return DTO de respuesta con información del movimiento.
   */
  public static MovimientoAhorroResponseDTO toMovimientoDTO(
      final MovimientoAhorro movimiento
  ) {

    return new MovimientoAhorroResponseDTO(
        movimiento.getId(),
        movimiento.getMonto(),
        movimiento.getOrigen(),
        movimiento.getCreatedAt()
    );
  }

  /**
   * Calcula el porcentaje de avance de una meta.
   *
   * @param meta meta utilizada para el cálculo.
   * @return porcentaje de avance calculado.
   */
  private static BigDecimal calcularPorcentajeAvance(
      final Meta meta
  ) {

    if (BigDecimal.ZERO.compareTo(meta.getCostoObjetivo()) == 0) {
      return BigDecimal.ZERO;
    }

    return meta.getAhorroAcumulado()
        .multiply(CIEN)
        .divide(
            meta.getCostoObjetivo(),
            BusinessConstants.DECIMAL_SCALE,
            BusinessConstants.DECIMAL_ROUNDING_MODE
        );
  }

}