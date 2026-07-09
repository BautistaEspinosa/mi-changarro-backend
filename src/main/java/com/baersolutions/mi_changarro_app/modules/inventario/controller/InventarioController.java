package com.baersolutions.mi_changarro_app.modules.inventario.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.InventarioMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.InventarioRoutes;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador del módulo Inventario.
 */
@RestController
@RequestMapping(InventarioRoutes.BASE)
@RequiredArgsConstructor
public class InventarioController {

  private final InventarioService inventarioService;

  @GetMapping
  public ApiResponse<List<ProductoResponseDTO>> obtenerInventario() {
    return ApiResponse.success(
        InventarioMessages.MSG_INVENTARIO_ACTUAL,
        inventarioService.obtenerInventario()
    );
  }

  @GetMapping(InventarioRoutes.STOCK_BAJO)
  public ApiResponse<List<ProductoResponseDTO>> obtenerStockBajo() {
    return ApiResponse.success(
        InventarioMessages.MSG_STOCK_BAJO,
        inventarioService.obtenerStockBajo()
    );
  }

  @GetMapping(InventarioRoutes.RESUMEN)
  public ApiResponse<Long> contarProductos() {
    return ApiResponse.success(
        InventarioMessages.MSG_RESUMEN,
        inventarioService.obtenerTotalProductosActivos()
    );
  }
}