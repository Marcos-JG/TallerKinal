package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MecanicoMapper {
    MecanicoDto toDto(MecanicoEntity e);
    MecanicoEntity toEntity(MecanicoDto d);
}

