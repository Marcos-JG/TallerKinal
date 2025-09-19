package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.repository.CategoriaRepository;
import org.Algorix.TallerKinal.dominio.repository.MarcaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductoService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaProductoService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaProductoDto> obtenerTodo() {
        return categoriaRepository.obtenerTodo();
    }

    public CategoriaProductoDto obtenerCategoriaPorCodigo(Long codigo){
        return this.categoriaRepository.obtenerCategoriaPorId(codigo);
    }

    public CategoriaProductoDto guardarCategoria(CategoriaProductoDto categoriaProductoDto){
        return this.categoriaRepository.guardarCategoria(categoriaProductoDto);
    }

    public CategoriaProductoDto modificarCategoria(Long codigo, ModCategoriaDto modCategoriaDto){
        return this.categoriaRepository.modificarCategoria(codigo, modCategoriaDto);
    }

    public void eliminarCategoria(Long codigo){
        this.categoriaRepository.eliminarCategoria(codigo);
    }

}
