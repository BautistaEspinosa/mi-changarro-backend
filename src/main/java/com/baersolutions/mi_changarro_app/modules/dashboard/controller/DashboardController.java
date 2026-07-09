package com.baersolutions.mi_changarro_app.modules.dashboard.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.dashboard.constants.DashboardMessages;
import com.baersolutions.mi_changarro_app.modules.dashboard.constants.DashboardRoutes;
import com.baersolutions.mi_changarro_app.modules.dashboard.dto.response.DashboardResponseDTO;
import com.baersolutions.mi_changarro_app.modules.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST del módulo Dashboard.
 *
 * <p>Expone la consulta principal del Dashboard, consolidando la información
 * general del negocio sin modificar datos.</p>
 *
 * @author Roman Bautista Espinosa
 */
@RestController
@RequestMapping(DashboardRoutes.BASE)
@RequiredArgsConstructor
public class DashboardController {

  private final DashboardService dashboardService;

  /**
   * Obtiene el resumen general del Dashboard.
   *
   * @return respuesta HTTP con el resumen del negocio
   */
  @GetMapping
  public ApiResponse<DashboardResponseDTO> obtenerDashboard() {

    return ApiResponse.success(
        DashboardMessages.DASHBOARD_OBTENIDO,
        dashboardService.obtenerDashboard()
    );
  }

}