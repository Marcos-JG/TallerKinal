package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastName")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "contrasena", target = "password")
    public ClienteDto toDto(ClienteEntity entity);
    public List<ClienteDto> toDtos(Iterable<ClienteEntity> entities);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastName", target = "apellido")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    public ClienteEntity toEntity(ClienteDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    void modificarEntityFromDto(ModClienteDto modClienteDto, @MappingTarget ClienteEntity clienteEntity);

    @Mapping(source = "email", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    void iniciarEntityFromDto (UserClienteDto userClienteDto, @MappingTarget ClienteEntity clienteEntity);
}
