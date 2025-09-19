package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.persistence.entity.CitaEntity;
import org.Algorix.TallerKinal.persistence.entity.TrabajoRealizadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TrabajoRealizadoMapper {

    // mapear id de la entidad al dto explícitamente para evitar que quede null
    @Mapping(source = "id_trabajo", target = "id_trabajo")
    @Mapping(source = "cita.id_cita" , target = "idCita")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "manoObra", target = "laborCost")
    @Mapping(source = "totalTrabajo", target = "totalWork")
    public TrabajoRealizadoDto toDto(TrabajoRealizadoEntity entity);
    public List<TrabajoRealizadoDto> toDtos(Iterable<TrabajoRealizadoEntity> entities);

    @Mapping(source = "id_trabajo", target = "id_trabajo")
    @Mapping(source = "idCita", target = "cita.id_cita")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "laborCost", target = "manoObra")
    @Mapping(source = "totalWork", target = "totalTrabajo")
    public TrabajoRealizadoEntity toEntity(TrabajoRealizadoDto dto);

    @Named("mapIdCitaToCitaEntity")
    default CitaEntity mapIdCitaToCitaEntity(Long idCita) {
        if (idCita == null) return null;
        CitaEntity cita = new CitaEntity();
        cita.setId_cita(idCita);
        return cita;
    }


    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "laborCost", target = "manoObra")
    @Mapping(source = "totalWork", target = "totalTrabajo")
    void modificarEntityFromDto(ModTrabajoRealozadoDto dto, @MappingTarget TrabajoRealizadoEntity entity);
}
