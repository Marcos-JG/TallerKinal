package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.repository.TrabajoRealizadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajoRealizadoService {
    private final TrabajoRealizadoRepository trabajoRealizadoRepository;

    public TrabajoRealizadoService(TrabajoRealizadoRepository trabajoRealizadoRepository) {
        this.trabajoRealizadoRepository = trabajoRealizadoRepository;
    }

    public List<TrabajoRealizadoDto> listarTrabajoRealizados(){
        return this.trabajoRealizadoRepository.listarTrabajos();
    }

    public TrabajoRealizadoDto buscarPorId(Long id){
        return this.trabajoRealizadoRepository.buscarPorId(id);
    }

    public TrabajoRealizadoDto guardarTrabajo(TrabajoRealizadoDto trabajoRealizadoDto){
        return this.trabajoRealizadoRepository.guardarTrabajo(trabajoRealizadoDto);
    }

    public TrabajoRealizadoDto modificarTrabajo(Long id, ModTrabajoRealozadoDto trabajoRealizadoDto){
        return this.trabajoRealizadoRepository.modificarTrabajo(id, trabajoRealizadoDto);
    }
    public void eliminarTrabajo(Long id){
        this.trabajoRealizadoRepository.eliminarTrabajo(id);
    }

}
