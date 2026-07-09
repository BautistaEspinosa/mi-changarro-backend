package com.baersolutions.mi_changarro_app.modules.dashboard.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.dashboard.constants.DashboardMessages;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.DashboardResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.OrigenDineroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenDeudasResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenDineroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenInventarioResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenMetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.ResumenVentasResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.mapper.DashboardMapper;
import com.baersolutions.mi_changarro_app.modules.dashboard.service.DashboardService;
import com.baersolutions.mi_changarro_app.modules.inventario.service.InventarioService;
import com.baersolutions.mi_changarro_app.modules.medeben.service.DeudaService;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.service.MetaService;
import com.baersolutions.mi_changarro_app.modules.ventas.service.VentaService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Dashboard.
 *
 * <p>Consolida información proveniente de Ventas, Me Deben, Metas e Inventario
 * para construir una vista general del estado actual del negocio.</p>
 *
 * @author Roman Bautista Espinosa
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

  private final VentaService ventaService;
  private final DeudaService deudaService;
  private final MetaService metaService;
  private final InventarioService inventarioService;

  /**
   * Obtiene el resumen general del negocio.
   *
   * @return información consolidada del Dashboard
   */
  @Override
  @Transactional(readOnly = true)
  public DashboardResponseDTO obtenerDashboard() {

    log.info(
        LogMessages.START,
        DashboardMessages.MODULE,
        DashboardMessages.OP_OBTENER
    );

    BigDecimal ventasCobradas = ventaService.obtenerTotalVentasCobradas();
    BigDecimal ventasPendientes = ventaService.obtenerTotalVentasPendientes();
    BigDecimal ingresosProductosPropios = ventaService.obtenerIngresosProductosPropios();
    BigDecimal ingresosBetterware = ventaService.obtenerIngresosBetterware();
    BigDecimal dineroRecuperado = deudaService.obtenerDineroRecuperado();
    BigDecimal dineroPendientePorCobrar = deudaService.obtenerDineroPendientePorCobrar();
    BigDecimal costoReposicionProductosVendidos =
        ventaService.obtenerCostoReposicionProductosVendidos();

    MetaResponseDTO metaActiva = obtenerMetaActivaParaDashboard();

    long totalProductosActivos = inventarioService.obtenerTotalProductosActivos();
    long productosConStockBajo = inventarioService.obtenerTotalProductosConStockBajo();

    ResumenDineroResponseDTO resumenDinero = construirResumenDinero(
        ventasCobradas,
        dineroRecuperado,
        costoReposicionProductosVendidos,
        metaActiva
    );

    OrigenDineroResponseDTO origenDinero = construirOrigenDinero(
        ingresosProductosPropios,
        ingresosBetterware
    );

    ResumenVentasResponseDTO resumenVentas = construirResumenVentas(
        ventasCobradas,
        ventasPendientes
    );

    ResumenDeudasResponseDTO resumenDeudas = construirResumenDeudas(
        dineroPendientePorCobrar
    );

    ResumenMetaResponseDTO resumenMeta = construirResumenMeta(
        metaActiva
    );

    ResumenInventarioResponseDTO resumenInventario = construirResumenInventario(
        productosConStockBajo,
        totalProductosActivos
    );

    DashboardResponseDTO response = DashboardMapper.toDashboard(
        resumenDinero,
        origenDinero,
        resumenVentas,
        resumenDeudas,
        resumenMeta,
        resumenInventario
    );

    log.info(
        LogMessages.SUCCESS,
        DashboardMessages.MODULE,
        DashboardMessages.OP_OBTENER
    );

    return response;
  }

  /**
   * Construye el resumen del dinero real del negocio.
   *
   * @param ventasCobradas total de ventas cobradas
   * @param dineroRecuperado dinero recuperado por deudas pagadas
   * @param costoReposicionProductosVendidos costo para volver a comprar
   * @param metaActiva meta activa actual
   * @return resumen del dinero del negocio
   */
  private ResumenDineroResponseDTO construirResumenDinero(
      BigDecimal ventasCobradas,
      BigDecimal dineroRecuperado,
      BigDecimal costoReposicionProductosVendidos,
      MetaResponseDTO metaActiva
  ) {

    BigDecimal tengo = calcularTengo(
        ventasCobradas,
        dineroRecuperado
    );

    BigDecimal loQueRealmenteGane = tengo.subtract(
        costoReposicionProductosVendidos
    );

    BigDecimal guardadoParaMiMeta = obtenerGuardadoParaMiMeta(
        metaActiva
    );

    BigDecimal disponibleParaGastar = calcularDisponibleParaGastar(
        loQueRealmenteGane,
        guardadoParaMiMeta
    );

    return DashboardMapper.toResumenDinero(
        tengo,
        costoReposicionProductosVendidos,
        loQueRealmenteGane,
        guardadoParaMiMeta,
        disponibleParaGastar
    );
  }

  /**
   * Construye el resumen del origen del dinero.
   *
   * @param ingresosProductosPropios ingresos por productos propios
   * @param ingresosBetterware ingresos por Betterware
   * @return origen del dinero
   */
  private OrigenDineroResponseDTO construirOrigenDinero(
      BigDecimal ingresosProductosPropios,
      BigDecimal ingresosBetterware
  ) {

    return DashboardMapper.toOrigenDinero(
        ingresosProductosPropios,
        ingresosBetterware
    );
  }

  /**
   * Construye el resumen de ventas.
   *
   * @param ventasCobradas total de ventas cobradas
   * @param ventasPendientes total de ventas pendientes
   * @return resumen de ventas
   */
  private ResumenVentasResponseDTO construirResumenVentas(
      BigDecimal ventasCobradas,
      BigDecimal ventasPendientes
  ) {

    return DashboardMapper.toResumenVentas(
        ventasCobradas,
        ventasPendientes
    );
  }

  /**
   * Construye el resumen de deudas.
   *
   * @param dineroPendientePorCobrar dinero pendiente por cobrar
   * @return resumen de deudas
   */
  private ResumenDeudasResponseDTO construirResumenDeudas(
      BigDecimal dineroPendientePorCobrar
  ) {

    return DashboardMapper.toResumenDeudas(
        dineroPendientePorCobrar
    );
  }

  /**
   * Construye el resumen de la meta activa.
   *
   * @param metaActiva meta activa actual
   * @return resumen de la meta activa
   */
  private ResumenMetaResponseDTO construirResumenMeta(
      MetaResponseDTO metaActiva
  ) {

    if (metaActiva == null) {
      return DashboardMapper.toResumenMeta(
          null,
          BigDecimal.ZERO,
          BigDecimal.ZERO,
          BigDecimal.ZERO,
          null
      );
    }

    return DashboardMapper.toResumenMeta(
        metaActiva.nombre(),
        metaActiva.costoObjetivo(),
        metaActiva.ahorroAcumulado(),
        metaActiva.porcentajeAvance(),
        metaActiva.fechaLimite()
    );
  }

  /**
   * Construye el resumen del inventario.
   *
   * @param productosConStockBajo total de productos con stock bajo
   * @param totalProductosActivos total de productos activos
   * @return resumen del inventario
   */
  private ResumenInventarioResponseDTO construirResumenInventario(
      long productosConStockBajo,
      long totalProductosActivos
  ) {

    return DashboardMapper.toResumenInventario(
        Math.toIntExact(productosConStockBajo),
        Math.toIntExact(totalProductosActivos)
    );
  }

  /**
   * Calcula el dinero real que ya entró al negocio.
   *
   * @param ventasCobradas total de ventas cobradas
   * @param dineroRecuperado dinero recuperado por deudas pagadas
   * @return dinero real disponible
   */
  private BigDecimal calcularTengo(
      BigDecimal ventasCobradas,
      BigDecimal dineroRecuperado
  ) {

    return ventasCobradas.add(dineroRecuperado);
  }

  /**
   * Obtiene el dinero guardado para la meta activa.
   *
   * @param metaActiva meta activa actual
   * @return ahorro acumulado de la meta activa
   */
  private BigDecimal obtenerGuardadoParaMiMeta(
      MetaResponseDTO metaActiva
  ) {

    if (metaActiva == null) {
      return BigDecimal.ZERO;
    }

    return metaActiva.ahorroAcumulado();
  }

  /**
   * Calcula el dinero disponible para gastar.
   *
   * <p>Si el resultado es negativo, se devuelve cero para evitar mostrar dinero
   * negativo al usuario.</p>
   *
   * @param loQueRealmenteGane ganancia real calculada
   * @param guardadoParaMiMeta ahorro acumulado para la meta
   * @return dinero disponible para gastar
   */
  private BigDecimal calcularDisponibleParaGastar(
      BigDecimal loQueRealmenteGane,
      BigDecimal guardadoParaMiMeta
  ) {

    BigDecimal disponible = loQueRealmenteGane.subtract(
        guardadoParaMiMeta
    );

    if (disponible.compareTo(BigDecimal.ZERO) < 0) {
      return BigDecimal.ZERO;
    }

    return disponible;
  }

  /**
   * Obtiene la meta activa para el Dashboard.
   *
   * <p>Si no existe una meta activa, devuelve null para permitir que el
   * Dashboard se muestre con valores vacíos en el bloque de meta.</p>
   *
   * @return meta activa o null si no existe
   */
  private MetaResponseDTO obtenerMetaActivaParaDashboard() {

    try {
      return metaService.obtenerMetaActiva();
    } catch (ResourceNotFoundException exception) {
      return null;
    }
  }
}