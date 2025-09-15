package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.persistence.entity.CategoriaProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaProductoMapper {
    CategoriaProductoDto toDto(CategoriaProductoEntity e);
    CategoriaProductoEntity toEntity(CategoriaProductoDto d);
}

