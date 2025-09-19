package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.repository.CitaRepository;
import org.Algorix.TallerKinal.persistence.entity.CitaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    public List<CitaDto> obtenerTodo() {
        return this.citaRepository.obtenerTodo();
    }
    public CitaDto obtenerTodoPorId(Long id) {
        return this.citaRepository.buscarPorId(id);
    }

    public CitaDto guardarCita(CitaDto citaDto) {
        return this.citaRepository.guardarCita(citaDto);
    }

    public CitaDto modificarCita(Long id, ModCitaDto modCitaDto) {
        return this.citaRepository.modificarCita(id, modCitaDto);
    }
    public void eliminarCita(Long id) {
        this.citaRepository.eliminarCita(id);
    }
}
