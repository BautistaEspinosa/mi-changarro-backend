package com.baersolutions.mi_changarro_app.modules.medeben.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.InvalidOperationException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.medeben.constants.MeDebenMessages;
import com.baersolutions.mi_changarro_app.modules.medeben.dto.response.DeudaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;
import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import com.baersolutions.mi_changarro_app.modules.medeben.mapper.DeudaMapper;
import com.baersolutions.mi_changarro_app.modules.medeben.repository.DeudaRepository;
import com.baersolutions.mi_changarro_app.modules.medeben.service.DeudaService;
import com.baersolutions.mi_changarro_app.modules.metas.service.MetaService;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.TipoVenta;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Me Deben.
 *
 * <p>Contiene la lógica de negocio para crear deudas desde ventas FIADO,
 * consultar deudas registradas y marcar deudas como pagadas sin modificar la
 * venta histórica que originó la deuda.
 *
 * @author Baer Solutions
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeudaServiceImpl implements DeudaService {

  private final DeudaRepository deudaRepository;
  private final MetaService metaService;
  /**
   * Crea una deuda a partir de una venta FIADO.
   *
   * @param venta venta FIADO que originará la deuda.
   * @return deuda creada.
   */
  @Override
  @Transactional
  public DeudaResponseDTO crearDesdeVenta(final Venta venta) {

    log.info(
        LogMessages.START,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_CREAR_DEUDA
    );

    if (!TipoVenta.FIADO.equals(venta.getTipoVenta())) {
      throw new BusinessException(
          MeDebenMessages.VENTA_NO_ES_FIADO
      );
    }

    Deuda deuda = Deuda.builder()
        .venta(venta)
        .nombreCliente(venta.getNombreCliente())
        .montoPendiente(venta.getTotalVenta())
        .fechaCompra(venta.getCreatedAt())
        .estado(EstadoDeuda.PENDIENTE)
        .build();

    Deuda deudaGuardada = deudaRepository.save(deuda);

    log.info(
        LogMessages.SUCCESS,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_CREAR_DEUDA
    );

    return DeudaMapper.toDTO(deudaGuardada);
  }

  /**
   * Obtiene una deuda por su identificador.
   *
   * @param id identificador de la deuda.
   * @return deuda encontrada.
   */
  @Override
  @Transactional(readOnly = true)
  public DeudaResponseDTO obtenerPorId(final Long id) {

    log.info(
        LogMessages.START,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_OBTENER_DEUDA
    );

    Deuda deuda = deudaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            MeDebenMessages.DEUDA_NO_ENCONTRADA
        ));

    log.info(
        LogMessages.SUCCESS,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_OBTENER_DEUDA
    );

    return DeudaMapper.toDTO(deuda);
  }

  /**
   * Obtiene todas las deudas registradas.
   *
   * @return lista de deudas.
   */
  @Override
  @Transactional(readOnly = true)
  public List<DeudaResponseDTO> listarDeudas() {

    log.info(
        LogMessages.START,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_LISTAR_DEUDAS
    );

    List<DeudaResponseDTO> deudas = deudaRepository.findAllByOrderByCreatedAtDesc()
        .stream()
        .map(DeudaMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_LISTAR_DEUDAS
    );

    return deudas;
  }

  /**
   * Obtiene únicamente las deudas pendientes.
   *
   * @return lista de deudas pendientes.
   */
  @Override
  @Transactional(readOnly = true)
  public List<DeudaResponseDTO> listarPendientes() {

    log.info(
        LogMessages.START,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_LISTAR_DEUDAS
    );

    List<DeudaResponseDTO> deudas = deudaRepository
        .findByEstadoOrderByCreatedAtDesc(EstadoDeuda.PENDIENTE)
        .stream()
        .map(DeudaMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_LISTAR_DEUDAS
    );

    return deudas;
  }

  /**
   * Marca una deuda como pagada.
   *
   * @param id identificador de la deuda.
   * @return deuda actualizada.
   */
  @Override
  @Transactional
  public DeudaResponseDTO marcarComoPagada(final Long id) {

    log.info(
        LogMessages.START,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_MARCAR_COMO_PAGADA
    );

    Deuda deuda = deudaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            MeDebenMessages.DEUDA_NO_ENCONTRADA
        ));

    if (EstadoDeuda.PAGADA.equals(deuda.getEstado())) {
      throw new InvalidOperationException(
          MeDebenMessages.DEUDA_YA_PAGADA
      );
    }

    deuda.setEstado(EstadoDeuda.PAGADA);
    deuda.setFechaPago(LocalDateTime.now());

    Deuda deudaGuardada = deudaRepository.save(deuda);

    metaService.registrarAhorroDesdeDeudaPagada(deudaGuardada);

    log.info(
        LogMessages.SUCCESS,
        MeDebenMessages.MODULE,
        MeDebenMessages.OP_MARCAR_COMO_PAGADA
    );

    return DeudaMapper.toDTO(deudaGuardada);
  }

}