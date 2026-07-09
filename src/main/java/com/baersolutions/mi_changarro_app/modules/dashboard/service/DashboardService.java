package com.baersolutions.mi_changarro_app.modules.dashboard.service;

import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.DashboardResponseDTO;

/**
 * Define las operaciones del módulo Dashboard.
 *
 * <p>El Dashboard consolida información proveniente de los módulos de
 * Ventas, Me Deben, Metas e Inventario para mostrar el estado general
 * del negocio.</p>
 *
 * @author Roman Bautista Espinosa
 */
public interface DashboardService {

  /**
   * Obtiene el resumen general del negocio.
   *
   * @return información consolidada del Dashboard
   */
  DashboardResponseDTO obtenerDashboard();

}