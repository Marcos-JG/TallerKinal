package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaProductoMapper {

    @Mapping(source = "id_marca", target = "id_marca")
    @Mapping(source = "nombre", target = "name")
    MarcaProductoDto toDto(MarcaProductoEntity e);
    public List<MarcaProductoDto> toDto(Iterable <MarcaProductoEntity> entities);

    MarcaProductoEntity toEntity(MarcaProductoDto d);
}

