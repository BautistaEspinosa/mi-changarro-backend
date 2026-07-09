package com.baersolutions.mi_changarro_app.modules.dashboard.dto.response;

import java.math.BigDecimal;

/**
 * Representa el resumen del dinero real del negocio mostrado en el Dashboard.
 *
 * <p>Contiene los principales indicadores financieros del MVP expresados con
 * lenguaje sencillo para el usuario final.</p>
 *
 * @param tengo dinero real disponible en el negocio
 * @param paraVolverAComprar dinero reservado para reinvertir en mercancía
 * @param loQueRealmenteGane ganancia real obtenida
 * @param guardadoParaMiMeta dinero ahorrado para la meta activa
 * @param disponibleParaGastar dinero libre para gastar
 *
 * @author Roman Bautista Espinosa
 */
public record ResumenDineroResponseDTO(

    BigDecimal tengo,

    BigDecimal paraVolverAComprar,

    BigDecimal loQueRealmenteGane,

    BigDecimal guardadoParaMiMeta,

    BigDecimal disponibleParaGastar

) {
}