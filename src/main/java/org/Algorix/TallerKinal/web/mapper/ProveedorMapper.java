package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.persistence.entity.ProveedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    ProveedorMapper INSTANCE = Mappers.getMapper(ProveedorMapper.class);

    @Mapping(source = "id_proveedor", target = "id_proveedor")
    @Mapping(source = "nombreEmpresa", target = "commpanyName")
    @Mapping(source = "contacto", target = "contact")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "correo", target = "email")
    ProveedorDto toDto(ProveedorEntity entity);

    @Mapping(source = "id_proveedor", target = "id_proveedor")
    @Mapping(source = "commpanyName", target = "nombreEmpresa")
    @Mapping(source = "contact", target = "contacto")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "email", target = "correo")
    ProveedorEntity toEntity(ProveedorDto dto);

    List<ProveedorDto> toDto(List<ProveedorEntity> entities);
}
