package com.baersolutions.mi_changarro_app.modules.metas.service;

import com.baersolutions.mi_changarro_app.modules.medeben.entity.Deuda;
import com.baersolutions.mi_changarro_app.modules.metas.dto.request.MetaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MovimientoAhorroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.ventas.entity.Venta;
import java.util.List;

/**
 * Define las operaciones del módulo Metas.
 *
 * <p>Permite crear metas de ahorro, consultar la meta activa, revisar el
 * historial de metas y registrar ahorro automático cuando entra dinero real al
 * negocio.
 *
 * @author Baer Solutions
 */
public interface MetaService {

  /**
   * Crea una nueva meta de ahorro.
   *
   * <p>Al crear una meta, el sistema desactiva cualquier meta activa anterior
   * para garantizar que solo exista una meta activa a la vez.
   *
   * @param dto información capturada por el usuario.
   * @return meta creada.
   */
  MetaResponseDTO crearMeta(
      MetaRequestDTO dto
  );

  /**
   * Obtiene una meta por su identificador.
   *
   * @param id identificador de la meta.
   * @return meta encontrada.
   */
  MetaResponseDTO obtenerPorId(
      Long id
  );

  /**
   * Obtiene la meta activa actual.
   *
   * @return meta activa.
   */
  MetaResponseDTO obtenerMetaActiva();

  /**
   * Obtiene el historial de metas registradas.
   *
   * @return lista de metas.
   */
  List<MetaResponseDTO> listarHistorial();

  /**
   * Obtiene los movimientos de ahorro asociados a una meta.
   *
   * @param id identificador de la meta.
   * @return lista de movimientos de ahorro.
   */
  List<MovimientoAhorroResponseDTO> listarMovimientos(
      Long id
  );

  /**
   * Registra ahorro automático desde una venta cobrada.
   *
   * <p>Solo las ventas con dinero realmente cobrado pueden aportar ahorro a la
   * meta activa. Las ventas FIADO no generan ahorro mientras estén pendientes.
   *
   * @param venta venta cobrada que genera ahorro.
   */
  void registrarAhorroDesdeVenta(
      Venta venta
  );

  /**
   * Registra ahorro automático desde una deuda pagada.
   *
   * <p>Cuando una deuda se marca como pagada, ese dinero ya puede considerarse
   * ingreso real y aportar a la meta activa.
   *
   * @param deuda deuda pagada que genera ahorro.
   */
  void registrarAhorroDesdeDeudaPagada(
      Deuda deuda
  );

}