package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;

import java.util.List;

public interface CategoriaRepository {
    List<CategoriaProductoDto> obtenerTodo();
    CategoriaProductoDto obtenerCategoriaPorId(Long codigo);
    void eliminarCategoria(Long codigo);
    CategoriaProductoDto modificarCategoria(Long codigo, ModCategoriaDto modCategoriaDto);
    CategoriaProductoDto guardarCategoria(CategoriaProductoDto categoriaProductoDto);
}
