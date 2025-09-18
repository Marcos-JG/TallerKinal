package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.persistence.entity.AdministradorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    AdministradorDto toDto(AdministradorEntity e);
    AdministradorEntity toEntity(AdministradorDto d);
}
