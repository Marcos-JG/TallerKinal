package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.persistence.entity.CitaEntity;

import java.util.List;

public interface CitaRepository {
    List<CitaDto> obtenerTodo();
    CitaDto buscarPorId(Long id);
    CitaDto guardarCita(CitaDto citaDto);
    CitaDto modificarCita(Long id, ModCitaDto modCitaDto);
    void eliminarCita(Long id);
}
