package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.persistence.entity.ProveedorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    ProveedorDto toDto(ProveedorEntity e);
    ProveedorEntity toEntity(ProveedorDto d);
}

