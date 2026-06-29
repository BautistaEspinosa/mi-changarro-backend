package com.baersolutions.mi_changarro_app.modules.inventario.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.constants.InventarioMessages;
import com.baersolutions.mi_changarro_app.modules.inventario.dto.response.ProductoResponseDTO;
import com.baersolutions.mi_changarro_app.modules.inventario.mapper.ProductoMapper;
import com.baersolutions.mi_changarro_app.modules.inventario.repository.ProductoRepository;
import com.baersolutions.mi_changarro_app.modules.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de consulta de inventario.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

  private final ProductoRepository productoRepository;

  @Override
  public List<ProductoResponseDTO> obtenerInventario() {

    log.info(LogMessages.START,
        InventarioMessages.MODULE,
        InventarioMessages.OP_INVENTARIO_TOTAL
    );

    List<ProductoResponseDTO> result = productoRepository.findByActivoTrue()
        .stream()
        .map(ProductoMapper::toDTO)
        .toList();

    log.info(LogMessages.SUCCESS,
        InventarioMessages.MODULE,
        InventarioMessages.OP_INVENTARIO_TOTAL
    );

    return result;
  }

  @Override
  public List<ProductoResponseDTO> obtenerStockBajo() {

    log.info(LogMessages.START,
        InventarioMessages.MODULE,
        InventarioMessages.OP_STOCK_BAJO
    );

    List<ProductoResponseDTO> result =
        productoRepository.findByActivoTrueAndStockActualLessThanEqual(
                BusinessConstants.STOCK_BAJO_DEFAULT
            )
            .stream()
            .map(ProductoMapper::toDTO)
            .toList();

    log.info(LogMessages.SUCCESS,
        InventarioMessages.MODULE,
        InventarioMessages.OP_STOCK_BAJO
    );

    return result;
  }

  @Override
  public long contarProductosActivos() {

    log.info(LogMessages.START,
        InventarioMessages.MODULE,
        InventarioMessages.OP_RESUMEN
    );

    long result = productoRepository.countByActivoTrue();

    log.info(LogMessages.SUCCESS,
        InventarioMessages.MODULE,
        InventarioMessages.OP_RESUMEN
    );

    return result;
  }
}