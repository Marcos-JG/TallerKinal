package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto toDto(ClienteEntity e);
    ClienteEntity toEntity(ClienteDto d);
}
