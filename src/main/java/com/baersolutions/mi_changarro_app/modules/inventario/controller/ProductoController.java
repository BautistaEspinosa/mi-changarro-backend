package com.baersolutions.mi_changarro_app.modules.inventario.controller;

import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoCreateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @PostMapping
  public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoCreateRequestDTO dto) {
    return ResponseEntity.ok(productoService.crearProducto(dto));
  }

  @GetMapping
  public ResponseEntity<List<ProductoResponseDTO>> listar() {
    return ResponseEntity.ok(productoService.listarActivos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductoResponseDTO> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(productoService.obtenerPorId(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductoResponseDTO> actualizar(
      @PathVariable Long id,
      @Valid @RequestBody ProductoUpdateRequestDTO dto
  ) {
    return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> desactivar(@PathVariable Long id) {
    productoService.desactivarProducto(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/activar")
  public ResponseEntity<Void> activar(@PathVariable Long id) {
    productoService.activarProducto(id);
    return ResponseEntity.noContent().build();
  }
}