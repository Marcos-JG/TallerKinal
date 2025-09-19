package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.persistence.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

    @Mapping(source = "cliente.id_cliente", target = "idCliente")
    @Mapping(source = "placas", target = "licensePlate")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "marca", target = "marca")
    @Mapping(source = "modelo", target = "model")
    @Mapping(source = "ano", target = "year")
    VehiculoDto toDto(VehiculoEntity e);
    List<VehiculoDto> toDto(Iterable<VehiculoEntity> entities);
    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "mapIdClienteToClienteEntity")
    @Mapping(target = "placas", source = "licensePlate")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "marca", source = "marca")
    @Mapping(target = "modelo", source = "model")
    @Mapping(target = "ano", source = "year")
    VehiculoEntity toEntity(VehiculoDto vehiculoDto);

    @Named("mapIdClienteToClienteEntity")
    default ClienteEntity mapIdClienteToClienteEntity(Long idCliente) {
        if (idCliente == null) return null;
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId_cliente(idCliente);
        return cliente;
    }

    @Mapping(target = "color", source = "color")
    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "mapIdClienteToClienteEntity")
    void modificarEntityFromDto(ModVehiculoDto modVehiculoDto, @MappingTarget VehiculoEntity vehiculoEntity);
}