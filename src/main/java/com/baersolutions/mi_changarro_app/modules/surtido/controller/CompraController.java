package com.baersolutions.mi_changarro_app.modules.surtido.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoMessages;
import com.baersolutions.mi_changarro_app.modules.surtido.constants.SurtidoRoutes;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.CompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.service.CompraService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del módulo Surtido.
 *
 * <p>Expone los endpoints necesarios para registrar compras de mercancía,
 * consultar una compra específica y obtener el historial de compras del
 * negocio.
 *
 * @author Baer Solutions
 */
@RestController
@RequestMapping(SurtidoRoutes.BASE)
@RequiredArgsConstructor
public class CompraController {

  private final CompraService compraService;

  /**
   * Registra una nueva compra de surtido.
   *
   * <p>Recibe la información de los productos adquiridos y delega el
   * procesamiento a la capa de servicio.
   *
   * @param dto información de la compra a registrar.
   * @return compra registrada.
   */
  @PostMapping
  public ApiResponse<CompraResponseDTO> registrarCompra(
      @Valid @RequestBody CompraRequestDTO dto
  ) {

    return ApiResponse.success(
        SurtidoMessages.COMPRA_REGISTRADA,
        compraService.registrarCompra(dto)
    );
  }

  /**
   * Obtiene una compra por su identificador.
   *
   * @param id identificador de la compra.
   * @return compra encontrada.
   */
  @GetMapping(SurtidoRoutes.BY_ID)
  public ApiResponse<CompraResponseDTO> obtenerCompraPorId(@PathVariable Long id) {

    return ApiResponse.success(
        SurtidoMessages.COMPRA_OBTENIDA,
        compraService.obtenerCompraPorId(id)
    );
  }

  /**
   * Lista el historial de compras registradas.
   *
   * @return historial de compras.
   */
  @GetMapping(SurtidoRoutes.HISTORIAL)
  public ApiResponse<List<CompraResponseDTO>> listarCompras() {

    return ApiResponse.success(
        SurtidoMessages.COMPRAS_OBTENIDAS,
        compraService.listarCompras()
    );
  }

}