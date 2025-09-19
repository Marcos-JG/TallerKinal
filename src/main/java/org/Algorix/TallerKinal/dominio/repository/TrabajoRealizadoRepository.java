package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;

import java.util.List;

public interface TrabajoRealizadoRepository {
    List<TrabajoRealizadoDto> listarTrabajos();
    TrabajoRealizadoDto buscarPorId(Long id);
    TrabajoRealizadoDto guardarTrabajo(TrabajoRealizadoDto trabajoRealizadoDto);
    TrabajoRealizadoDto modificarTrabajo(Long id, ModTrabajoRealozadoDto trabajoRealizadoDto);
    void eliminarTrabajo(Long id);
}
