package com.baersolutions.mi_changarro_app.modules.metas.service.impl;

import com.baersolutions.mi_changarro_app.common.constants.BusinessConstants;
import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.exception.BusinessException;
import com.baersolutions.mi_changarro_app.common.exception.ResourceNotFoundException;
import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;
import com.baersolutions.mi_changarro_app.modules.medeben.enums.EstadoDeuda;
import com.baersolutions.mi_changarro_app.modules.metas.constants.MetaMessages;
import com.baersolutions.mi_changarro_app.modules.metas.dto.request.MetaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MovimientoAhorroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.entity.Meta;
import com.baersolutions.mi_changarro_app.modules.metas.entity.MovimientoAhorro;
import com.baersolutions.mi_changarro_app.modules.metas.enums.OrigenMovimientoAhorro;
import com.baersolutions.mi_changarro_app.modules.metas.mapper.MetaMapper;
import com.baersolutions.mi_changarro_app.modules.metas.repository.MetaRepository;
import com.baersolutions.mi_changarro_app.modules.metas.repository.MovimientoAhorroRepository;
import com.baersolutions.mi_changarro_app.modules.metas.service.MetaService;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import com.baersolutions.mi_changarro_app.modules.ventas.enums.EstadoVenta;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio del módulo Metas.
 *
 * <p>Contiene la lógica de negocio para crear metas de ahorro, calcular el
 * ahorro requerido diario, consultar el progreso de la meta activa y registrar
 * movimientos de ahorro automático cuando entra dinero real al negocio.
 *
 * @author Baer Solutions
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MetaServiceImpl implements MetaService {

  private final MetaRepository metaRepository;

  private final MovimientoAhorroRepository movimientoAhorroRepository;

  /**
   * Crea una nueva meta de ahorro.
   *
   * <p>Solo puede existir una meta activa a la vez. Si existe una meta activa,
   * ésta será desactivada automáticamente antes de registrar la nueva.
   *
   * @param dto información capturada por el usuario.
   * @return meta creada.
   */
  @Override
  @Transactional
  public MetaResponseDTO crearMeta(final MetaRequestDTO dto) {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_CREAR
    );

    validarFechaLimite(dto.fechaLimite());

    metaRepository.findByActiva(Boolean.TRUE)
        .ifPresent(metaActiva -> {
          metaActiva.setActiva(Boolean.FALSE);
          metaRepository.save(metaActiva);
        });

    BigDecimal ahorroRequerido = calcularAhorroRequerido(
        dto.costoObjetivo(),
        dto.fechaLimite()
    );

    Meta meta = Meta.builder()
        .nombre(dto.nombre())
        .costoObjetivo(dto.costoObjetivo())
        .fechaLimite(dto.fechaLimite())
        .ahorroRequerido(ahorroRequerido)
        .ahorroAcumulado(BigDecimal.ZERO)
        .activa(Boolean.TRUE)
        .build();

    Meta metaGuardada = metaRepository.save(meta);

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_CREAR
    );

    return MetaMapper.toDTO(metaGuardada);
  }

  /**
   * Obtiene una meta por su identificador.
   *
   * @param id identificador de la meta.
   * @return meta encontrada.
   */
  @Override
  @Transactional(readOnly = true)
  public MetaResponseDTO obtenerPorId(final Long id) {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_OBTENER
    );

    Meta meta = obtenerMetaPorId(id);

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_OBTENER
    );

    return MetaMapper.toDTO(meta);
  }

  /**
   * Obtiene la meta activa actual.
   *
   * @return meta activa.
   */
  @Override
  @Transactional(readOnly = true)
  public MetaResponseDTO obtenerMetaActiva() {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_META_ACTIVA
    );

    Meta meta = metaRepository.findByActiva(Boolean.TRUE)
        .orElseThrow(() -> new ResourceNotFoundException(
            MetaMessages.META_ACTIVA_NO_ENCONTRADA
        ));

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_META_ACTIVA
    );

    return MetaMapper.toDTO(meta);
  }

  /**
   * Obtiene el historial de metas registradas.
   *
   * @return lista de metas.
   */
  @Override
  @Transactional(readOnly = true)
  public List<MetaResponseDTO> listarHistorial() {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_LISTAR
    );

    List<MetaResponseDTO> metas = metaRepository.findAllByOrderByCreatedAtDesc()
        .stream()
        .map(MetaMapper::toDTO)
        .toList();

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_LISTAR
    );

    return metas;
  }

  /**
   * Obtiene los movimientos de ahorro asociados a una meta.
   *
   * @param id identificador de la meta.
   * @return lista de movimientos de ahorro.
   */
  @Override
  @Transactional(readOnly = true)
  public List<MovimientoAhorroResponseDTO> listarMovimientos(final Long id) {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_MOVIMIENTOS
    );

    Meta meta = obtenerMetaPorId(id);

    List<MovimientoAhorroResponseDTO> movimientos =
        movimientoAhorroRepository.findByMetaOrderByCreatedAtDesc(meta)
            .stream()
            .map(MetaMapper::toMovimientoDTO)
            .toList();

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_MOVIMIENTOS
    );

    return movimientos;
  }

  /**
   * Registra ahorro automático desde una venta cobrada.
   *
   * <p>Las ventas FIADO no generan ahorro mientras estén pendientes. Solo las
   * ventas con estado COBRADO representan dinero real para el módulo Metas.
   *
   * @param venta venta cobrada que puede generar ahorro.
   */
  @Override
  @Transactional
  public void registrarAhorroDesdeVenta(final Venta venta) {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_AHORRO_AUTOMATICO
    );

    if (!EstadoVenta.COBRADO.equals(venta.getEstadoVenta())) {
      return;
    }

    registrarAhorroAutomatico(
        OrigenMovimientoAhorro.VENTA_COBRADA
    );

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_AHORRO_AUTOMATICO
    );
  }

  /**
   * Registra ahorro automático desde una deuda pagada.
   *
   * <p>Una deuda solo aporta ahorro cuando ya fue marcada como PAGADA, porque
   * hasta ese momento el dinero realmente entró al negocio.
   *
   * @param deuda deuda pagada que puede generar ahorro.
   */
  @Override
  @Transactional
  public void registrarAhorroDesdeDeudaPagada(final Deuda deuda) {

    log.info(
        LogMessages.START,
        MetaMessages.MODULE,
        MetaMessages.OP_AHORRO_AUTOMATICO
    );

    if (!EstadoDeuda.PAGADA.equals(deuda.getEstado())) {
      return;
    }

    registrarAhorroAutomatico(
        OrigenMovimientoAhorro.DEUDA_PAGADA
    );

    log.info(
        LogMessages.SUCCESS,
        MetaMessages.MODULE,
        MetaMessages.OP_AHORRO_AUTOMATICO
    );
  }

  /**
   * Registra ahorro automático para la meta activa.
   *
   * <p>Si no existe meta activa, no se registra ningún movimiento. Si la meta
   * ya alcanzó su objetivo, tampoco se genera ahorro adicional.
   *
   * @param origen origen del dinero que generó el ahorro.
   */
  private void registrarAhorroAutomatico(
      final OrigenMovimientoAhorro origen
  ) {

    metaRepository.findByActiva(Boolean.TRUE)
        .ifPresent(meta -> {
          BigDecimal montoAhorro = calcularMontoARegistrar(meta);

          if (BigDecimal.ZERO.compareTo(montoAhorro) >= 0) {
            return;
          }

          guardarMovimientoAhorro(
              meta,
              montoAhorro,
              origen
          );

          actualizarAhorroMeta(
              meta,
              montoAhorro
          );
        });
  }

  /**
   * Guarda un movimiento de ahorro automático.
   *
   * @param meta meta asociada al movimiento.
   * @param montoAhorro monto que será apartado.
   * @param origen origen del dinero.
   */
  private void guardarMovimientoAhorro(
      final Meta meta,
      final BigDecimal montoAhorro,
      final OrigenMovimientoAhorro origen
  ) {

    MovimientoAhorro movimiento = MovimientoAhorro.builder()
        .meta(meta)
        .monto(montoAhorro)
        .origen(origen)
        .build();

    movimientoAhorroRepository.save(movimiento);
  }

  /**
   * Actualiza el ahorro acumulado de la meta.
   *
   * <p>Cuando el ahorro acumulado alcanza el costo objetivo, la meta se marca
   * como inactiva automáticamente para evitar nuevos movimientos sobre una meta
   * ya completada.
   *
   * @param meta meta a actualizar.
   * @param montoAhorro monto ahorrado.
   */
  private void actualizarAhorroMeta(
      final Meta meta,
      final BigDecimal montoAhorro
  ) {

    BigDecimal nuevoAhorroAcumulado = meta.getAhorroAcumulado()
        .add(montoAhorro)
        .setScale(
            BusinessConstants.DECIMAL_SCALE,
            BusinessConstants.DECIMAL_ROUNDING_MODE
        );

    if (nuevoAhorroAcumulado.compareTo(meta.getCostoObjetivo()) >= 0) {
      meta.setAhorroAcumulado(meta.getCostoObjetivo());
      meta.setActiva(Boolean.FALSE);
    } else {
      meta.setAhorroAcumulado(nuevoAhorroAcumulado);
    }

    metaRepository.save(meta);
  }

  /**
   * Calcula el monto que debe registrarse como ahorro automático.
   *
   * <p>El monto registrado nunca puede exceder lo que falta para completar la
   * meta. Si la meta ya está completa, devuelve cero.
   *
   * @param meta meta activa.
   * @return monto a registrar.
   */
  private BigDecimal calcularMontoARegistrar(final Meta meta) {

    BigDecimal faltante = meta.getCostoObjetivo()
        .subtract(meta.getAhorroAcumulado());

    if (BigDecimal.ZERO.compareTo(faltante) >= 0) {
      return BigDecimal.ZERO;
    }

    if (meta.getAhorroRequerido().compareTo(faltante) > 0) {
      return faltante.setScale(
          BusinessConstants.DECIMAL_SCALE,
          BusinessConstants.DECIMAL_ROUNDING_MODE
      );
    }

    return meta.getAhorroRequerido().setScale(
        BusinessConstants.DECIMAL_SCALE,
        BusinessConstants.DECIMAL_ROUNDING_MODE
    );
  }

  /**
   * Calcula el ahorro diario requerido para alcanzar la meta.
   *
   * <p>Durante la creación de la meta, el campo {@code createdAt} aún no ha sido
   * asignado por JPA. Por ello se utiliza {@link LocalDate#now()} como fecha
   * efectiva de creación para calcular los días disponibles.
   *
   * @param costoObjetivo costo total de la meta.
   * @param fechaLimite fecha límite definida por el usuario.
   * @return ahorro diario requerido.
   */
  private BigDecimal calcularAhorroRequerido(
      final BigDecimal costoObjetivo,
      final LocalDate fechaLimite
  ) {

    long diasDisponibles = ChronoUnit.DAYS.between(
        LocalDate.now(),
        fechaLimite
    );

    if (diasDisponibles <= 0) {
      throw new BusinessException(
          MetaMessages.FECHA_LIMITE_INVALIDA
      );
    }

    return costoObjetivo.divide(
        BigDecimal.valueOf(diasDisponibles),
        BusinessConstants.DECIMAL_SCALE,
        BusinessConstants.DECIMAL_ROUNDING_MODE
    );
  }

  /**
   * Valida que la fecha límite sea posterior a la fecha actual.
   *
   * @param fechaLimite fecha límite a validar.
   */
  private void validarFechaLimite(final LocalDate fechaLimite) {

    if (!fechaLimite.isAfter(LocalDate.now())) {
      throw new BusinessException(
          MetaMessages.FECHA_LIMITE_INVALIDA
      );
    }
  }

  /**
   * Obtiene una meta por ID o lanza excepción si no existe.
   *
   * @param id identificador de la meta.
   * @return meta encontrada.
   */
  private Meta obtenerMetaPorId(final Long id) {

    return metaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            MetaMessages.META_NO_ENCONTRADA
        ));
  }

}