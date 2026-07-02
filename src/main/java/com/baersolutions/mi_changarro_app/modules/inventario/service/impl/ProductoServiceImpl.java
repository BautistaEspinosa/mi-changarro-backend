package com.baersolutions.mi_changarro_app.modules.inventario.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.mapper.ProductoMapper;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de Producto.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  @Override
  public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_CREAR
    );

    if (productoRepository.findByNombre(dto.nombre()).isPresent()) {
      throw new BusinessException(ProductoMessages.PRODUCTO_DUPLICADO);
    }

    Producto producto = ProductoMapper.toEntity(dto);
    Producto saved = productoRepository.save(producto);

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_CREAR
    );

    return ProductoMapper.toDTO(saved);
  }

  @Override
  public ProductoResponseDTO obtenerPorId(Long id) {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_OBTENER
    );

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            ProductoMessages.PRODUCTO_NO_ENCONTRADO
        ));

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_OBTENER
    );

    return ProductoMapper.toDTO(producto);
  }

  @Override
  public List<ProductoResponseDTO> obtenerActivos() {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_LISTAR
    );

    List<ProductoResponseDTO> productos = productoRepository.findByActivoTrue()
        .stream()
        .map(ProductoMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_LISTAR
    );

    return productos;
  }

  @Override
  public List<ProductoResponseDTO> buscarPorNombre(String nombre) {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_BUSCAR
    );

    List<ProductoResponseDTO> productos = productoRepository
        .findByNombreContainingIgnoreCase(nombre)
        .stream()
        .map(ProductoMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_BUSCAR
    );

    return productos;
  }

  @Override
  public ProductoResponseDTO desactivarProducto(Long id) {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_ELIMINAR
    );

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            ProductoMessages.PRODUCTO_NO_ENCONTRADO
        ));

    producto.setActivo(false);

    Producto saved = productoRepository.save(producto);

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_ELIMINAR
    );

    return ProductoMapper.toDTO(saved);
  }

  @Override
  public ProductoResponseDTO activarProducto(Long id) {

    log.info(
        LogMessages.START,
        ProductoMessages.MODULE,
        ProductoMessages.OP_BUSCAR
    );

    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            ProductoMessages.PRODUCTO_NO_ENCONTRADO
        ));

    producto.setActivo(true);

    Producto saved = productoRepository.save(producto);

    log.info(
        LogMessages.SUCCESS,
        ProductoMessages.MODULE,
        ProductoMessages.OP_ACTIVAR
    );

    return ProductoMapper.toDTO(saved);
  }

}