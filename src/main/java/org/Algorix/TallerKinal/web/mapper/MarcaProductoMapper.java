package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaProductoMapper {
    MarcaProductoDto toDto(MarcaProductoEntity e);
    MarcaProductoEntity toEntity(MarcaProductoDto d);
}

