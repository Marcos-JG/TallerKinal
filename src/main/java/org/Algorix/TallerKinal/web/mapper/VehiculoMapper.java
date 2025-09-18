package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.persistence.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

    @Mappings({
        @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "clienteFromId")
    })
    VehiculoEntity toEntity(VehiculoDto d);

    @Mappings({
        @Mapping(target = "idCliente", source = "cliente", qualifiedByName = "idFromCliente")
    })
    VehiculoDto toDto(VehiculoEntity e);

    @Named("clienteFromId")
    default ClienteEntity clienteFromId(Long id) {
        if (id == null) return null;
        ClienteEntity c = new ClienteEntity();
        c.id_cliente = id;
        return c;
    }

    @Named("idFromCliente")
    default Long idFromCliente(ClienteEntity e) {
        return e == null ? null : e.id_cliente;
    }
}

