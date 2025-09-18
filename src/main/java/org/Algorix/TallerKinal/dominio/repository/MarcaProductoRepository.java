package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;

import java.util.List;

public interface MarcaProductoRepository {
    List<MarcaProductoDto> obtenerTodo();
    MarcaProductoDto obtenerMarcaPorCodigo(Long codigo);

}
