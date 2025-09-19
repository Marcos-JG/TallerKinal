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
    @Mapping(source = "idCliente", qualifiedByName = "mapIdClienteToClienteEntity", target = "cliente")
    @Mapping(source = "licensePlate", target = "placas")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "marca", target = "marca")
    @Mapping(source = "model", target = "modelo")
    @Mapping(source = "year", target = "ano")
    VehiculoEntity toEntity(VehiculoDto vehiculoDto);

    @Named("mapIdClienteToClienteEntity")
    default ClienteEntity mapIdClienteToClienteEntity(Long idCliente) {
        if (idCliente == null) return null;
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId_cliente(idCliente);
        return cliente;
    }

    @Mapping(source = "color", target = "color")
    @Mapping(source = "idCliente", qualifiedByName = "mapIdClienteToClienteEntity", target = "cliente")
    void modificarEntityFromDto(ModVehiculoDto modVehiculoDto, @MappingTarget VehiculoEntity vehiculoEntity);
}