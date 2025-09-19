package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MecanicoMapper {


    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastName")
    @Mapping(source = "telefono", target = "phone")
    MecanicoDto toDto(MecanicoEntity e);

    public List<MecanicoDto> toDto(Iterable <MecanicoEntity> entities);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastName", target = "apellido")
    @Mapping(source = "phone", target = "telefono")
    MecanicoEntity toEntity(MecanicoDto d);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastName", target = "apellido")
    @Mapping(source = "phone", target = "telefono")
    void modificarEntityFromDto(ModMecanicoDto modMecanicoDto, @MappingTarget MecanicoEntity mecanicoEntity);

}
