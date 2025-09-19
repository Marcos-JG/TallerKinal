package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.persistence.entity.CategoriaProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaProductoMapper {

    @Mapping(source = "nombre", target = "name")
    CategoriaProductoDto toDto(CategoriaProductoEntity e);

    List<CategoriaProductoDto> toDto(Iterable <CategoriaProductoEntity> entities);

    @Mapping(source = "name", target = "nombre")
    CategoriaProductoEntity toEntity(CategoriaProductoDto d);

    @Mapping(source = "name", target = "nombre")
    void modificarEntityFromDto(ModCategoriaDto modCategoriaDto, @MappingTarget CategoriaProductoEntity categoriaProductoEntity);
}

