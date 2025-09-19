package org.Algorix.TallerKinal.persistence;

import jdk.jfr.Registered;
import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.repository.CitaRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudCita;
import org.Algorix.TallerKinal.persistence.entity.CitaEntity;
import org.Algorix.TallerKinal.web.mapper.CitaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitaEntityRepository implements CitaRepository {
    private final CrudCita crudCita;
    private final  CitaMapper citaMapper;

    public CitaEntityRepository(CrudCita crudCita, CitaMapper citaMapper) {
        this.crudCita = crudCita;
        this.citaMapper = citaMapper;
    }

    @Override
    public List<CitaDto> obtenerTodo() {
        return this.citaMapper.toDto(this.crudCita.findAll());
    }

    @Override
    public CitaDto buscarPorId(Long id) {
        CitaEntity citaEntity = this.crudCita.findById(id).orElse(null);
        return this.citaMapper.toDto(citaEntity);
    }

    @Override
    public CitaDto guardarCita(CitaDto citaDto) {
        CitaEntity cita = this.citaMapper.toEntity(citaDto);
        this.crudCita.save(cita);
        return this.citaMapper.toDto(cita);
    }


    @Override
    public CitaDto modificarCita(Long id, ModCitaDto modCitaDto) {
        return null;
    }

    @Override
    public void eliminarCita(Long id) {

    }
}
