package com.baersolutions.mi_changarro_app.modules.dashboard.mapper;

import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.DashboardResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.OrigenDineroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenDeudasResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenDineroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenInventarioResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenMetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenVentasResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Mapper del módulo Dashboard.
 *
 * <p>Construye los diferentes bloques de información utilizados por el
 * Dashboard a partir de valores previamente calculados por el servicio.</p>
 *
 * <p>No contiene lógica de negocio; únicamente realiza la construcción de
 * los ResponseDTO del módulo.</p>
 *
 * @author Roman Bautista Espinosa
 */
public final class DashboardMapper {

  private DashboardMapper() {
    throw new IllegalStateException("Utility Class");
  }

  /**
   * Construye el resumen del dinero.
   */
  public static ResumenDineroResponseDTO toResumenDinero(
      BigDecimal tengo,
      BigDecimal paraVolverAComprar,
      BigDecimal loQueRealmenteGane,
      BigDecimal guardadoParaMiMeta,
      BigDecimal disponibleParaGastar
  ) {

    return new ResumenDineroResponseDTO(
        tengo,
        paraVolverAComprar,
        loQueRealmenteGane,
        guardadoParaMiMeta,
        disponibleParaGastar
    );
  }

  /**
   * Construye el origen del dinero.
   */
  public static OrigenDineroResponseDTO toOrigenDinero(
      BigDecimal productosPropios,
      BigDecimal betterware
  ) {

    return new OrigenDineroResponseDTO(
        productosPropios,
        betterware
    );
  }

  /**
   * Construye el resumen de ventas.
   */
  public static ResumenVentasResponseDTO toResumenVentas(
      BigDecimal totalVentasCobradas,
      BigDecimal totalPendientePorCobrar
  ) {

    return new ResumenVentasResponseDTO(
        totalVentasCobradas,
        totalPendientePorCobrar
    );
  }

  /**
   * Construye el resumen de deudas.
   */
  public static ResumenDeudasResponseDTO toResumenDeudas(
      BigDecimal totalDeudasPendientes
  ) {

    return new ResumenDeudasResponseDTO(
        totalDeudasPendientes
    );
  }

  /**
   * Construye el resumen de la meta.
   */
  public static ResumenMetaResponseDTO toResumenMeta(
      String nombre,
      BigDecimal costoObjetivo,
      BigDecimal ahorroAcumulado,
      BigDecimal porcentajeAvance,
      LocalDate fechaLimite
  ) {

    return new ResumenMetaResponseDTO(
        nombre,
        costoObjetivo,
        ahorroAcumulado,
        porcentajeAvance,
        fechaLimite
    );
  }

  /**
   * Construye el resumen del inventario.
   */
  public static ResumenInventarioResponseDTO toResumenInventario(
      Integer productosConStockBajo,
      Integer totalProductosActivos
  ) {

    return new ResumenInventarioResponseDTO(
        productosConStockBajo,
        totalProductosActivos
    );
  }

  /**
   * Construye la respuesta principal del Dashboard.
   */
  public static DashboardResponseDTO toDashboard(
      ResumenDineroResponseDTO resumenDinero,
      OrigenDineroResponseDTO origenDinero,
      ResumenVentasResponseDTO resumenVentas,
      ResumenDeudasResponseDTO resumenDeudas,
      ResumenMetaResponseDTO resumenMeta,
      ResumenInventarioResponseDTO resumenInventario
  ) {

    return new DashboardResponseDTO(
        resumenDinero,
        origenDinero,
        resumenVentas,
        resumenDeudas,
        resumenMeta,
        resumenInventario
    );
  }

}