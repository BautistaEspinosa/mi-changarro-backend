package com.baersolutions.mi_changarro_app.modules.metas.controller;

import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import com.baersolutions.mi_changarro_app.modules.metas.constants.MetaMessages;
import com.baersolutions.mi_changarro_app.modules.metas.constants.MetaRoutes;
import com.baersolutions.mi_changarro_app.modules.metas.dto.request.MetaRequestDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MetaResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.dto.response.MovimientoAhorroResponseDTO;
import com.baersolutions.mi_changarro_app.modules.metas.service.MetaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST del módulo Metas.
 *
 * <p>Expone endpoints para crear metas de ahorro, consultar la meta activa,
 * obtener historial y revisar movimientos de ahorro automático.
 *
 * @author Baer Solutions
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(MetaRoutes.BASE)
public class MetaController {

  private final MetaService metaService;

  /**
   * Crea una nueva meta de ahorro.
   *
   * @param dto información capturada por el usuario.
   * @return meta creada.
   */
  @PostMapping
  public ApiResponse<MetaResponseDTO> crearMeta(
      @Valid @RequestBody final MetaRequestDTO dto
  ) {

    MetaResponseDTO response = metaService.crearMeta(dto);

    return ApiResponse.created(
        MetaMessages.META_CREADA,
        response
    );
  }

  /**
   * Obtiene una meta por su identificador.
   *
   * @param id identificador de la meta.
   * @return meta encontrada.
   */
  @GetMapping(MetaRoutes.BY_ID)
  public ApiResponse<MetaResponseDTO> obtenerPorId(
      @PathVariable final Long id
  ) {

    MetaResponseDTO response = metaService.obtenerPorId(id);

    return ApiResponse.success(
        MetaMessages.META_OBTENIDA,
        response
    );
  }

  /**
   * Obtiene la meta activa actual.
   *
   * @return meta activa.
   */
  @GetMapping(MetaRoutes.ACTIVA)
  public ApiResponse<MetaResponseDTO> obtenerMetaActiva() {

    MetaResponseDTO response = metaService.obtenerMetaActiva();

    return ApiResponse.success(
        MetaMessages.META_ACTIVA_OBTENIDA,
        response
    );
  }

  /**
   * Obtiene el historial de metas registradas.
   *
   * @return lista de metas.
   */
  @GetMapping(MetaRoutes.HISTORIAL)
  public ApiResponse<List<MetaResponseDTO>> listarHistorial() {

    List<MetaResponseDTO> response = metaService.listarHistorial();

    return ApiResponse.success(
        MetaMessages.METAS_LISTADAS,
        response
    );
  }

  /**
   * Obtiene los movimientos de ahorro de una meta.
   *
   * @param id identificador de la meta.
   * @return lista de movimientos.
   */
  @GetMapping(MetaRoutes.MOVIMIENTOS)
  public ApiResponse<List<MovimientoAhorroResponseDTO>> listarMovimientos(
      @PathVariable final Long id
  ) {

    List<MovimientoAhorroResponseDTO> response =
        metaService.listarMovimientos(id);

    return ApiResponse.success(
        MetaMessages.MOVIMIENTOS_OBTENIDOS,
        response
    );
  }

}