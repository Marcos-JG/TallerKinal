package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.persistence.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mappings({
        @Mapping(target = "empleado", source = "idEmpleado", qualifiedByName = "mecanicoFromId"),
        @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "clienteFromId"),
        @Mapping(target = "vehiculo", source = "idVehiculo", qualifiedByName = "vehiculoFromId")
    })
    CitaEntity toEntity(CitaDto d);

    @Mappings({
        @Mapping(target = "idEmpleado", source = "empleado", qualifiedByName = "idFromMecanico"),
        @Mapping(target = "idCliente", source = "cliente", qualifiedByName = "idFromCliente"),
        @Mapping(target = "idVehiculo", source = "vehiculo", qualifiedByName = "idFromVehiculo")
    })
    CitaDto toDto(CitaEntity e);

    @Named("mecanicoFromId")
    default MecanicoEntity mecanicoFromId(Long id) {
        if (id == null) return null;
        MecanicoEntity m = new MecanicoEntity();
        m.id_mecanico = id;
        return m;
    }

    @Named("clienteFromId")
    default ClienteEntity clienteFromId(Long id) {
        if (id == null) return null;
        ClienteEntity c = new ClienteEntity();
        c.id_cliente = id;
        return c;
    }

    @Named("vehiculoFromId")
    default VehiculoEntity vehiculoFromId(Long id) {
        if (id == null) return null;
        VehiculoEntity v = new VehiculoEntity();
        v.id_vehiculo = id;
        return v;
    }

    @Named("idFromMecanico")
    default Long idFromMecanico(MecanicoEntity e) { return e == null ? null : e.id_mecanico; }

    @Named("idFromCliente")
    default Long idFromCliente(ClienteEntity e) { return e == null ? null : e.id_cliente; }

    @Named("idFromVehiculo")
    default Long idFromVehiculo(VehiculoEntity e) { return e == null ? null : e.id_vehiculo; }
}

