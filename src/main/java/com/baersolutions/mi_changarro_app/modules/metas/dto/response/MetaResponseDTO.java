package com.baersolutions.mi_changarro_app.modules.metas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para una meta de ahorro.
 *
 * <p>Representa la información que el sistema devuelve al cliente sobre una
 * meta, incluyendo valores calculados como el porcentaje de avance. La fecha
 * de creación proviene del campo {@code createdAt} heredado de BaseEntity.
 *
 * @param id identificador de la meta.
 * @param nombre nombre de la meta.
 * @param costoObjetivo monto objetivo definido por el usuario.
 * @param ahorroRequerido monto calculado que el sistema debe apartar
 *                        automáticamente por período.
 * @param ahorroAcumulado monto ahorrado hasta el momento.
 * @param porcentajeAvance porcentaje de progreso de la meta.
 * @param fechaLimite fecha límite definida para cumplir la meta.
 * @param fechaCreacion fecha en que se creó la meta.
 * @param activa indica si la meta se encuentra activa.
 *
 * @author Baer Solutions
 */
public record MetaResponseDTO(

    Long id,
    String nombre,
    BigDecimal costoObjetivo,
    BigDecimal ahorroRequerido,
    BigDecimal ahorroAcumulado,
    BigDecimal porcentajeAvance,
    LocalDate fechaLimite,
    LocalDateTime fechaCreacion,
    Boolean activa

) {
}