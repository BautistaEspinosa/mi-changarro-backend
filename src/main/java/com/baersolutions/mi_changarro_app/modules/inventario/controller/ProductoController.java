package com.baersolutions.mi_changarro_app.modules.inventario.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoRoutes;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controlador del módulo Producto. */
@RestController
@RequestMapping(ProductoRoutes.BASE)
@RequiredArgsConstructor
public class ProductoController {

  private final ProductoService productoService;

  @PostMapping
  public ApiResponse<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {

    return ApiResponse.created(
        ProductoMessages.PRODUCTO_CREADO, productoService.crearProducto(dto));
  }

  @GetMapping(ProductoRoutes.BY_ID)
  public ApiResponse<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_OBTENIDO, productoService.obtenerPorId(id));
  }

  @GetMapping
  public ApiResponse<List<ProductoResponseDTO>> listarActivos() {

    return ApiResponse.success(
        ProductoMessages.PRODUCTOS_LISTADOS, productoService.obtenerActivos());
  }

  @GetMapping(ProductoRoutes.INACTIVOS)
  public ApiResponse<List<ProductoResponseDTO>> listarInactivos() {

    return ApiResponse.success(
        ProductoMessages.PRODUCTOS_INACTIVOS_LISTADOS, productoService.obtenerInactivos());
  }

  @GetMapping(ProductoRoutes.BUSCAR)
  public ApiResponse<List<ProductoResponseDTO>> buscar(@RequestParam String nombre) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTOS_LISTADOS, productoService.buscarPorNombre(nombre));
  }

  @PutMapping(ProductoRoutes.DESACTIVAR)
  public ApiResponse<ProductoResponseDTO> desactivar(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_ACTUALIZADO, productoService.desactivarProducto(id));
  }

  @PutMapping(ProductoRoutes.ACTIVAR)
  public ApiResponse<ProductoResponseDTO> activar(@PathVariable Long id) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_ACTUALIZADO, productoService.activarProducto(id));
  }

  @PutMapping(ProductoRoutes.BY_ID)
  public ApiResponse<ProductoResponseDTO> actualizar(
      @PathVariable Long id, @Valid @RequestBody ProductoUpdateRequestDTO dto) {

    return ApiResponse.success(
        ProductoMessages.PRODUCTO_ACTUALIZADO, productoService.actualizarProducto(id, dto));
  }
}
