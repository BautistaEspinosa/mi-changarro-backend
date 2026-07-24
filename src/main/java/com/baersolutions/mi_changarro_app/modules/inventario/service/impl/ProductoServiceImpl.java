package com.baersolutions.mi_changarro_app.modules.inventario.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.ProductoMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.request.ProductoUpdateRequestDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.entity.Producto;
import com.baersolutions.mi_changarro_app.modules.inventario.mapper.ProductoMapper;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.inventario.service.ProductoService;
import com.baersolutions.mi_changarro_app.modules.inventario.util.ProductoNombreNormalizer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Implementación del servicio de Producto. */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  @Override
  public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_CREAR);

    String nombreVisible = ProductoNombreNormalizer.limpiarNombreVisible(dto.nombre());

    String nombreComparacion = ProductoNombreNormalizer.normalizarParaComparacion(nombreVisible);

    boolean productoDuplicado =
        productoRepository.findAll().stream()
            .map(Producto::getNombre)
            .map(ProductoNombreNormalizer::normalizarParaComparacion)
            .anyMatch(nombreExistente -> nombreExistente.equals(nombreComparacion));

    if (productoDuplicado) {
      throw new BusinessException(ProductoMessages.PRODUCTO_DUPLICADO);
    }

    Producto producto = ProductoMapper.toEntity(dto);
    producto.setNombre(nombreVisible);

    Producto saved = productoRepository.save(producto);
    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_CREAR);

    return ProductoMapper.toDTO(saved);
  }

  @Override
  public ProductoResponseDTO obtenerPorId(Long id) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_OBTENER);

    Producto producto =
        productoRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(ProductoMessages.PRODUCTO_NO_ENCONTRADO));

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_OBTENER);

    return ProductoMapper.toDTO(producto);
  }

  @Override
  public List<ProductoResponseDTO> obtenerActivos() {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_LISTAR);

    List<ProductoResponseDTO> productos =
        productoRepository.findByActivoTrue().stream().map(ProductoMapper::toDTO).toList();

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_LISTAR);

    return productos;
  }

  @Override
  public List<ProductoResponseDTO> obtenerInactivos() {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_LISTAR);

    List<ProductoResponseDTO> productos =
        productoRepository.findByActivoFalse().stream().map(ProductoMapper::toDTO).toList();

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_LISTAR);

    return productos;
  }

  @Override
  public List<ProductoResponseDTO> buscarPorNombre(String nombre) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_BUSCAR);

    String nombreComparacion = ProductoNombreNormalizer.normalizarParaComparacion(nombre);

    List<ProductoResponseDTO> productos =
        productoRepository.findAll().stream()
            .filter(
                producto ->
                    ProductoNombreNormalizer.normalizarParaComparacion(producto.getNombre())
                        .contains(nombreComparacion))
            .map(ProductoMapper::toDTO)
            .toList();

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_BUSCAR);

    return productos;
  }

  @Override
  public ProductoResponseDTO desactivarProducto(Long id) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_ELIMINAR);

    Producto producto =
        productoRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(ProductoMessages.PRODUCTO_NO_ENCONTRADO));

    producto.setActivo(Boolean.FALSE);

    Producto saved = productoRepository.save(producto);

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_ELIMINAR);

    return ProductoMapper.toDTO(saved);
  }

  @Override
  public ProductoResponseDTO activarProducto(Long id) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_ACTIVAR);

    Producto producto =
        productoRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(ProductoMessages.PRODUCTO_NO_ENCONTRADO));

    producto.setActivo(Boolean.TRUE);

    Producto saved = productoRepository.save(producto);

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_ACTIVAR);

    return ProductoMapper.toDTO(saved);
  }

  @Override
  public ProductoResponseDTO actualizarProducto(Long id, ProductoUpdateRequestDTO dto) {

    log.info(LogMessages.START, ProductoMessages.MODULE, ProductoMessages.OP_ACTUALIZAR);

    Producto producto =
        productoRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(ProductoMessages.PRODUCTO_NO_ENCONTRADO));

    String nombreVisible = ProductoNombreNormalizer.limpiarNombreVisible(dto.nombre());

    String nombreComparacion = ProductoNombreNormalizer.normalizarParaComparacion(nombreVisible);

    boolean productoDuplicado =
        productoRepository.findAll().stream()
            .filter(productoExistente -> !productoExistente.getId().equals(id))
            .map(Producto::getNombre)
            .map(ProductoNombreNormalizer::normalizarParaComparacion)
            .anyMatch(nombreExistente -> nombreExistente.equals(nombreComparacion));

    if (productoDuplicado) {
      throw new BusinessException(ProductoMessages.PRODUCTO_DUPLICADO);
    }

    producto.setNombre(nombreVisible);
    producto.setStockMinimo(dto.stockMinimo());

    Producto saved = productoRepository.save(producto);

    log.info(LogMessages.SUCCESS, ProductoMessages.MODULE, ProductoMessages.OP_ACTUALIZAR);

    return ProductoMapper.toDTO(saved);
  }
}
