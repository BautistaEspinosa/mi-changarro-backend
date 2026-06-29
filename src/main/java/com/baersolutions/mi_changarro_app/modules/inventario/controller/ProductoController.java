package com.baersolutions.mi_changarro_app.modules.inventario.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoRoutes;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador del módulo Producto.
 */
@Slf4j
@RestController
@RequestMapping(ProductoRoutes.BASE)
@RequiredArgsConstructor
public class ProductoController {

  private final ProductoService productoService;

  @PostMapping
  public ApiResponse<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_CREADO,
        productoService.crearProducto(dto)
    );
  }

  @GetMapping(ProductoRoutes.BY_ID)
  public ApiResponse<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_OBTENIDO,
        productoService.obtenerPorId(id)
    );
  }

  @GetMapping
  public ApiResponse<List<ProductoResponseDTO>> listarActivos() {

    return ApiResponse.success(
        ProductoMessages.PRODUCTOS_LISTADOS,
        productoService.obtenerActivos()
    );
  }

  @GetMapping(ProductoRoutes.BUSCAR)
  public ApiResponse<List<ProductoResponseDTO>> buscar(@RequestParam String nombre) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTOS_LISTADOS,
        productoService.buscarPorNombre(nombre)
    );
  }

  @PutMapping(ProductoRoutes.DESACTIVAR)
  public ApiResponse<ProductoResponseDTO> desactivar(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_ACTUALIZADO,
        productoService.desactivarProducto(id)
    );
  }

  @PutMapping(ProductoRoutes.ACTIVAR)
  public ApiResponse<ProductoResponseDTO> activar(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_ACTUALIZADO,
        productoService.activarProducto(id)
    );
  }

}