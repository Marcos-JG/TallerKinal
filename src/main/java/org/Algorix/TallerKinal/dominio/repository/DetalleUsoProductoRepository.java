package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.persistence.entity.DetalleUsoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleUsoProductoRepository {
    List<DetalleUsoProductoDto> obtenerTodo();
    DetalleUsoProductoDto buscarPorCodigo(Long id);
    DetalleUsoProductoDto guardarDetalle(DetalleUsoProductoDto detalleUsoProductoDto);
    DetalleUsoProductoDto modificarDetalle(Long id, ModDetalleUsoProductoDto modDetalleUsoProductoDto);
    void eliminarDetalle(Long id);
}
