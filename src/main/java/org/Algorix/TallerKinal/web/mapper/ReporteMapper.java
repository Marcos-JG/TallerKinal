package org.Algorix.TallerKinal.web.mapper;



import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;
import org.Algorix.TallerKinal.persistence.entity.ReporteEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    @Mapping(source = "cita.id_cita", target = "idCita")
    @Mapping(source = "descripcionGeneral", target = "description")
    @Mapping(source = "total", target = "total")
    ReporteDto toDto(ReporteEntity e);


    List<ReporteDto> toDto(Iterable <ReporteEntity> entities);

    @Mapping(source = "idCita", target = "cita.id_cita")
    @Mapping(source = "description", target = "descripcionGeneral")
    @Mapping(source = "total", target = "total")
    ReporteEntity toEntity(ReporteDto d);

    @Mapping(source = "idCita", target = "cita.id_cita")
    @Mapping(source = "description", target = "descripcionGeneral")
    @Mapping(source = "total", target = "total")
    void modificarEntityFromDto(ModReporteDto modReporteDto,
                                @MappingTarget ReporteEntity reporteEntity);
}
