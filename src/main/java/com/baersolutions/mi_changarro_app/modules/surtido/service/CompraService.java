package com.baersolutions.mi_changarro_app.modules.surtido.service;

import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.CompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import java.util.List;

/**
 * Define las operaciones del módulo Surtido.
 *
 * <p>Expone los casos de uso relacionados con el registro y consulta de
 * compras realizadas para surtir el negocio.
 *
 * @author Baer Solutions
 */
public interface CompraService {

  /**
   * Registra una nueva compra de surtido.
   *
   * <p>La compra puede contener uno o varios productos y será procesada por el
   * servicio para calcular costos, actualizar inventario y generar la respuesta
   * correspondiente.
   *
   * @param dto información de la compra a registrar.
   * @return compra registrada con sus detalles.
   */
  CompraResponseDTO registrarCompra(CompraRequestDTO dto);

  /**
   * Obtiene una compra por su identificador.
   *
   * <p>Devuelve la información de la compra junto con los productos incluidos
   * en ella.
   *
   * @param id identificador de la compra.
   * @return compra encontrada.
   */
  CompraResponseDTO obtenerCompraPorId(Long id);

  /**
   * Lista el historial de compras registradas.
   *
   * <p>Devuelve las compras ordenadas de la más reciente a la más antigua.
   *
   * @return lista de compras registradas.
   */
  List<CompraResponseDTO> listarCompras();

}