package com.baersolutions.mi_changarro_app.modules.inventario.service.impl;

import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoCreateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.mapper.ProductoMapper;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public ProductoResponseDTO crearProducto(ProductoCreateRequestDTO dto) {

    log.info("Creando producto {}", dto.nombre());

    if (productoRepository.existsByNombreIgnoreCase(dto.nombre())) {
      throw new BusinessException("Ya existe un producto con ese nombre");
    }

    Producto producto = ProductoMapper.toEntity(dto);

    return ProductoMapper.toDTO(productoRepository.save(producto));
  }

  @Override
  public ProductoResponseDTO obtenerPorId(Long id) {

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

    return ProductoMapper.toDTO(producto);
  }

  @Override
  public List<ProductoResponseDTO> listarActivos() {
    return productoRepository.findByActivoTrue()
        .stream()
        .map(ProductoMapper::toDTO)
        .toList();
  }

  @Override
  public ProductoResponseDTO actualizarProducto(Long id, ProductoUpdateRequestDTO dto) {

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

    producto.setNombre(dto.nombre());
    producto.setStockMinimo(dto.stockMinimo());
    producto.setPrecioVentaActual(dto.precioVentaActual());

    return ProductoMapper.toDTO(productoRepository.save(producto));
  }

  @Override
  public void desactivarProducto(Long id) {

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

    producto.setActivo(false);
    productoRepository.save(producto);
  }

  @Override
  public void activarProducto(Long id) {

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

    producto.setActivo(true);
    productoRepository.save(producto);
  }
}