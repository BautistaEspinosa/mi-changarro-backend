package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa el resumen de la meta activa mostrado en el Dashboard.
 *
 * <p>Contiene únicamente la información necesaria para mostrar el progreso
 * actual de la meta de ahorro del usuario.</p>
 *
 * @param nombre nombre de la meta activa
 * @param costoObjetivo monto objetivo de la meta
 * @param ahorroAcumulado ahorro acumulado
 * @param porcentajeAvance porcentaje de avance de la meta
 * @param fechaLimite fecha límite para cumplir la meta
 *
 * @author Roman Bautista Espinosa
 */
public record ResumenMetaResponseDTO(

    String nombre,

    BigDecimal costoObjetivo,

    BigDecimal ahorroAcumulado,

    BigDecimal porcentajeAvance,

    LocalDate fechaLimite

) {
}