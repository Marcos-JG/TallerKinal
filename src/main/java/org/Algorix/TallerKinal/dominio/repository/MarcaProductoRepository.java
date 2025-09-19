package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;

import java.util.List;

public interface MarcaProductoRepository {
    List<MarcaProductoDto> obtenerTodo();
    MarcaProductoDto obtenerMarcaPorCodigo(Long codigo);
    void eliminarMarca(Long codigo);
    MarcaProductoDto modificarMarca(Long codigo, ModMarcaProductoDto modMarcaProductoDto);
    MarcaProductoDto guardarMarca(MarcaProductoDto marcaProductoDto);

}
