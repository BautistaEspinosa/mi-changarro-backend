package com.baersolutions.mi_changarro_app.modules.medeben.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.medeben.constants.MeDebenMessages;
import com.baersolutions.mi_changarro_app.modules.medeben.constants.MeDebenRoutes;
import com.baersolutions.mi_changarro_app.modules.medeben.dto.response.DeudaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.medeben.service.DeudaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST del módulo Me Deben.
 *
 * <p>Expone endpoints para consultar deudas y marcar una deuda como pagada.
 * La creación de deudas no se expone como endpoint público porque ocurre
 * automáticamente desde el módulo Ventas cuando se registra una venta FIADO.
 *
 * @author Baer Solutions
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(MeDebenRoutes.BASE)
public class DeudaController {

  private final DeudaService deudaService;

  /**
   * Lista todas las deudas registradas.
   *
   * @return respuesta estándar con el listado de deudas.
   */
  @GetMapping
  public ApiResponse<List<DeudaResponseDTO>> listarDeudas() {

    return ApiResponse.success(
        MeDebenMessages.DEUDAS_OBTENIDAS,
        deudaService.listarDeudas()
    );
  }

  /**
   * Lista únicamente las deudas pendientes.
   *
   * @return respuesta estándar con el listado de deudas pendientes.
   */
  @GetMapping(MeDebenRoutes.PENDIENTES)
  public ApiResponse<List<DeudaResponseDTO>> listarPendientes() {

    return ApiResponse.success(
        MeDebenMessages.DEUDAS_OBTENIDAS,
        deudaService.listarPendientes()
    );
  }

  /**
   * Obtiene una deuda por su identificador.
   *
   * @param id identificador de la deuda.
   * @return respuesta estándar con la deuda encontrada.
   */
  @GetMapping(MeDebenRoutes.BY_ID)
  public ApiResponse<DeudaResponseDTO> obtenerPorId(
      @PathVariable final Long id
  ) {

    return ApiResponse.success(
        MeDebenMessages.DEUDA_OBTENIDA,
        deudaService.obtenerPorId(id)
    );
  }

  /**
   * Marca una deuda como pagada.
   *
   * @param id identificador de la deuda.
   * @return respuesta estándar con la deuda actualizada.
   */
  @PatchMapping(MeDebenRoutes.PAGAR)
  public ApiResponse<DeudaResponseDTO> marcarComoPagada(
      @PathVariable final Long id
  ) {

    return ApiResponse.success(
        MeDebenMessages.DEUDA_PAGADA,
        deudaService.marcarComoPagada(id)
    );
  }

}