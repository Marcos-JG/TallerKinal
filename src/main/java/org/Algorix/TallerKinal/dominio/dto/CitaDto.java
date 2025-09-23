package org.Algorix.TallerKinal.dominio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record CitaDto (
        Long id_cita,

        @FutureOrPresent(message = "La fecha de la cita no puede ser pasada")
        LocalDate appointmentDate,

        @Min(value = 1, message = "El id del empleado debe ser válido")
        Long idEmpleado,

        @Min(value = 1, message = "El id del cliente debe ser válido")
        Long idCliente,

        @NotBlank(message = "El tipo de cita no puede estar vacio")
        String appointmentType,

        @Min(value = 1, message = "El id del vehiculo debe ser válido")
        Long idVehiculo,

        @NotBlank(message = "El estado de la cita no puede estar vacio")
        String status,

        @NotBlank (message = "Los comentarios no pueden estar vacios")
        String comments
){
}
