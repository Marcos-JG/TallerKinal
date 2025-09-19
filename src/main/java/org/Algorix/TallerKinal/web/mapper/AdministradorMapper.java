package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.persistence.entity.AdministradorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastname")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "telefono", target = "phone")
    public AdministradorDto toDto(AdministradorEntity entity);
    public List<AdministradorDto> toDtos(Iterable<AdministradorEntity> entities);

    public AdministradorEntity toEntity(UserAdminDto dto);

    @Mapping(source = "email", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    void iniciarEntityFromDto (UserAdminDto userAdminDto, @MappingTarget AdministradorEntity administradorEntity);
}
