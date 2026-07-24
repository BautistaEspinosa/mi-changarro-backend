package com.baersolutions.mi_changarro_app.modules.surtido.service;

import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.CompraRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.request.PrecioSugeridoRequestDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.CompraResponseDTO;
import com.baersolutions.mi_changarro_app.modules.surtido.dto.response.PrecioSugeridoResponseDTO;
import java.util.List;

/**
 * Define las operaciones del módulo Surtido.
 *
 * @author Baer Solutions
 */
public interface CompraService {

  /**
   * Calcula el costo unitario y los precios sugeridos de un producto.
   *
   * <p>Esta operación funciona como una previsualización y no modifica información en la base de
   * datos.
   *
   * @param dto información necesaria para realizar el cálculo.
   * @return costo unitario y precios sugeridos.
   */
  PrecioSugeridoResponseDTO calcularPrecios(PrecioSugeridoRequestDTO dto);

  /**
   * Registra una nueva compra de surtido.
   *
   * @param dto información de la compra a registrar.
   * @return compra registrada con sus detalles.
   */
  CompraResponseDTO registrarCompra(CompraRequestDTO dto);

  /**
   * Obtiene una compra por su identificador.
   *
   * @param id identificador de la compra.
   * @return compra encontrada.
   */
  CompraResponseDTO obtenerCompraPorId(Long id);

  /**
   * Lista el historial de compras registradas.
   *
   * @return lista de compras registradas.
   */
  List<CompraResponseDTO> listarCompras();
}
