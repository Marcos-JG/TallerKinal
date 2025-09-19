package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.MarcaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaProductoService {
    private final MarcaProductoRepository marcaProductoRepository;

    @Autowired
    public MarcaProductoService(MarcaProductoRepository marcaProductoRepository) {
        this.marcaProductoRepository = marcaProductoRepository;
    }

    public List<MarcaProductoDto> obtenerTodo() {
        return marcaProductoRepository.obtenerTodo();
    }

    public MarcaProductoDto obtenerMarcaPorCodigo(Long codigo){
        return this.marcaProductoRepository.obtenerMarcaPorCodigo(codigo);
    }

    public MarcaProductoDto guardarMarca(MarcaProductoDto marcaProductoDto){
        return this.marcaProductoRepository.guardarMarca(marcaProductoDto);
    }

    public MarcaProductoDto modificarMarca(Long codigo, ModMarcaProductoDto modMarcaProductoDto){
        return this.marcaProductoRepository.modificarMarca(codigo, modMarcaProductoDto);
    }

    public void eliminarMarca(Long codigo){
        this.marcaProductoRepository.eliminarMarca(codigo);
    }

}
