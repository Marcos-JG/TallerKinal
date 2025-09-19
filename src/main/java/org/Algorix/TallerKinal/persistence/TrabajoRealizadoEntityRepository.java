package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.exception.CitaNoExiste;
import org.Algorix.TallerKinal.persistence.crud.CrudCita;
import org.Algorix.TallerKinal.dominio.repository.TrabajoRealizadoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudTrabajoRealizado;
import org.Algorix.TallerKinal.persistence.entity.TrabajoRealizadoEntity;
import org.Algorix.TallerKinal.web.mapper.TrabajoRealizadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrabajoRealizadoEntityRepository implements TrabajoRealizadoRepository {

    private final CrudTrabajoRealizado crudTrabajoRealizado;
    private final CrudCita crudCita;
    private final TrabajoRealizadoMapper trabajoRealizadoMapper;

    public TrabajoRealizadoEntityRepository(CrudTrabajoRealizado crudTrabajoRealizado, CrudCita crudCita, TrabajoRealizadoMapper trabajoRealizadoMapper) {
        this.crudTrabajoRealizado = crudTrabajoRealizado;
        this.crudCita = crudCita;
        this.trabajoRealizadoMapper = trabajoRealizadoMapper;
    }

    @Override
    public List<TrabajoRealizadoDto> listarTrabajos() {
        return this.trabajoRealizadoMapper.toDtos(this.crudTrabajoRealizado.findAll());
    }

    @Override
    public TrabajoRealizadoDto buscarPorId(Long id) {
        TrabajoRealizadoEntity trabajoRealizadoEntity = this.crudTrabajoRealizado.findById(id).orElse(null);
        if (trabajoRealizadoEntity == null) {
            throw new RuntimeException("Trabajo no encontrado");
        }else {
            return trabajoRealizadoMapper.toDto(trabajoRealizadoEntity);
        }
    }

    @Override
    public TrabajoRealizadoDto guardarTrabajo(TrabajoRealizadoDto trabajoRealizadoDto) {
        TrabajoRealizadoEntity trabajo = this.trabajoRealizadoMapper.toEntity(trabajoRealizadoDto);
        // validar que la cita referenciada exista antes de guardar
        if (trabajo == null || trabajo.getCita() == null || trabajo.getCita().getId_cita() == null) {
            throw new CitaNoExiste(null);
        }
        Long idCita = trabajo.getCita().getId_cita();
        if (!this.crudCita.existsById(idCita)) {
            throw new CitaNoExiste(idCita);
        }
        this.crudTrabajoRealizado.save(trabajo);
        return this.trabajoRealizadoMapper.toDto(trabajo);
    }

    @Override
    public TrabajoRealizadoDto modificarTrabajo(Long id, ModTrabajoRealozadoDto trabajoRealizadoDto) {
        TrabajoRealizadoEntity trabajoEntity = this.crudTrabajoRealizado.findById(id).orElse(null);
        this.trabajoRealizadoMapper.modificarEntityFromDto(trabajoRealizadoDto, trabajoEntity);
        return this.trabajoRealizadoMapper.toDto(this.crudTrabajoRealizado.save(trabajoEntity));
    }

    @Override
    public void eliminarTrabajo(Long id) {
        TrabajoRealizadoEntity trabajoEntity = this.crudTrabajoRealizado.findById(id).orElse(null);
        if (trabajoEntity != null) {
            this.crudTrabajoRealizado.deleteById(id);
        } else {
            throw new RuntimeException("Trabajo no encontrado");
        }
    }
}
