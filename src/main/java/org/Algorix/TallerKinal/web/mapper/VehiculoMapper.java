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
    public List<VehiculoDto> toDto(Iterable<VehiculoEntity> entities);
    VehiculoEntity toEntity(VehiculoDto vehiculoDto);

    void modificarEntityFromDto(ModVehiculoDto modVehiculoDtoDto, @MappingTarget VehiculoEntity vehiculoEntity);
}