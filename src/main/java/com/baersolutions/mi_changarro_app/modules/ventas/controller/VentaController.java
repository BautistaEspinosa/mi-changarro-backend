package com.baersolutions.mi_changarro_app.modules.ventas.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaMessages;
import com.baersolutions.mi_changarro_app.modules.ventas.constants.VentaRoutes;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.request.VentaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.dto.response.VentaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.service.VentaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del módulo Ventas.
 *
 * <p>Expone los endpoints necesarios para registrar ventas híbridas,
 * consultar una venta específica y obtener el historial de ventas del negocio.
 *
 * @author Baer Solutions
 */
@RestController
@RequestMapping(VentaRoutes.BASE)
@RequiredArgsConstructor
public class VentaController {

  private final VentaService ventaService;

  /**
   * Registra una nueva venta híbrida.
   *
   * @param dto información de la venta a registrar.
   * @return venta registrada.
   */
  @PostMapping
  public ApiResponse<VentaResponseDTO> registrarVenta(
      @Valid @RequestBody VentaRequestDTO dto
  ) {

    return ApiResponse.created(
        VentaMessages.VENTA_REGISTRADA,
        ventaService.registrarVenta(dto)
    );
  }

  /**
   * Obtiene una venta por su identificador.
   *
   * @param id identificador de la venta.
   * @return venta encontrada.
   */
  @GetMapping(VentaRoutes.BY_ID)
  public ApiResponse<VentaResponseDTO> obtenerVentaPorId(@PathVariable Long id) {

    return ApiResponse.success(
        VentaMessages.VENTA_OBTENIDA,
        ventaService.obtenerVentaPorId(id)
    );
  }

  /**
   * Lista el historial de ventas registradas.
   *
   * @return historial de ventas.
   */
  @GetMapping(VentaRoutes.HISTORIAL)
  public ApiResponse<List<VentaResponseDTO>> listarVentas() {

    return ApiResponse.success(
        VentaMessages.VENTAS_OBTENIDAS,
        ventaService.listarVentas()
    );
  }
}