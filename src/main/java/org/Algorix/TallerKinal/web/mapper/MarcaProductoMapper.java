package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaProductoMapper {

    @Mapping(source = "nombre", target = "name")
    MarcaProductoDto toDto(MarcaProductoEntity e);

    public List<MarcaProductoDto> toDto(Iterable <MarcaProductoEntity> entities);

    @Mapping(source = "name", target = "nombre")
    MarcaProductoEntity toEntity(MarcaProductoDto d);

    @Mapping(source = "name", target = "nombre")
    void modificarEntityFromDto(ModMarcaProductoDto modMarcaProductoDto, @MappingTarget MarcaProductoEntity marcaProductoEntity);

}

