package org.Algorix.TallerKinal.web.mapper;

import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.persistence.entity.CitaEntity;
import org.Algorix.TallerKinal.persistence.entity.ClienteEntity;
import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.Algorix.TallerKinal.persistence.entity.VehiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    @Mapping(source = "fechaCita", target = "appointmentDate")
    @Mapping(source = "empleado.id_mecanico", target = "idEmpleado")
    @Mapping(source = "cliente.id_cliente", target = "idCliente")
    @Mapping(source = "tipoCita", target = "appointmentType")
    @Mapping(source = "vehiculo.id_vehiculo", target = "idVehiculo")
    @Mapping(source = "estadoCita", target = "status")
    @Mapping(source = "comentario", target = "comments")
    CitaDto toDto(CitaEntity entity);

    List<CitaDto> toDto(Iterable<CitaEntity> entities);

    @Mapping(source = "appointmentDate", target = "fechaCita")
    @Mapping(source = "idEmpleado", target = "empleado", qualifiedByName = "mapIdEmpleadoToEmpleadoEntity")
    @Mapping(source = "idCliente", target = "cliente" , qualifiedByName = "mapIdClienteToClienteEntity")
    @Mapping(source = "appointmentType", target = "tipoCita")
    @Mapping(source = "idVehiculo", target = "vehiculo", qualifiedByName = "mapIdVehiculoToVehiculoEntity")
    @Mapping(source = "status", target = "estadoCita")
    @Mapping(source = "comments", target = "comentario")
    CitaEntity toEntity(CitaDto citaDto);

    @Named("mapIdEmpleadoToEmpleadoEntity")
    default MecanicoEntity mapIdEmpleadoToEmpleadoEntity(Long idMecanico) {
        if (idMecanico == null) return null;
        MecanicoEntity mecanico = new MecanicoEntity();
        mecanico.setId_mecanico(idMecanico);
        return mecanico;
    }

    @Named("mapIdClienteToClienteEntity")
    default ClienteEntity mapIdClienteToClienteEntity(Long idCliente) {
        if (idCliente == null) return null;
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId_cliente(idCliente);
        return cliente;
    }

    @Named("mapIdVehiculoToVehiculoEntity")
    default VehiculoEntity mapIdVehiculoToVehiculoEntity(Long idVehiculo) {
        if (idVehiculo == null) return null;
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setId_vehiculo(idVehiculo);
        return vehiculo;
    }

    @Mapping(source = "appointmentDate", target = "fechaCita")
    @Mapping(source = "idEmpleado", target = "empleado" , qualifiedByName = "mapIdEmpleadoToEmpleadoEntity")
    @Mapping(source = "appointmentType", target = "tipoCita")
    @Mapping(source = "idVehiculo", target = "vehiculo", qualifiedByName = "mapIdVehiculoToVehiculoEntity")
    @Mapping(source = "status", target = "estadoCita")
    @Mapping(source = "comments", target = "comentario")
    void modificarEntityFromDto(ModCitaDto modCitaDto, @MappingTarget CitaEntity entity);
}
