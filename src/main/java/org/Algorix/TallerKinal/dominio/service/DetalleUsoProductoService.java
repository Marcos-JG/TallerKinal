package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.repository.DetalleUsoProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleUsoProductoService {
    private final DetalleUsoProductoRepository detalleUsoProductoRepository;

    public DetalleUsoProductoService(DetalleUsoProductoRepository detalleUsoProductoRepository) {
        this.detalleUsoProductoRepository = detalleUsoProductoRepository;
    }

    public List<DetalleUsoProductoDto> obtenerTodo() {
        return this.detalleUsoProductoRepository.obtenerTodo();
    }

    public DetalleUsoProductoDto obtenerDetallePorCodigo(Long codigo){
        return this.detalleUsoProductoRepository.buscarPorCodigo(codigo);
    }

    public DetalleUsoProductoDto guardarDetalle(DetalleUsoProductoDto detalleUsoProductoDto){
        return this.detalleUsoProductoRepository.guardarDetalle(detalleUsoProductoDto);

    }

    public DetalleUsoProductoDto modificarDetalle(Long codigo, ModDetalleUsoProductoDto modDetalleUsoProductoDto){
        return this.detalleUsoProductoRepository.modificarDetalle(codigo, modDetalleUsoProductoDto);
    }

    public void eliminarDetalle(Long codigo){
        this.detalleUsoProductoRepository.eliminarDetalle(codigo);
    }

}
