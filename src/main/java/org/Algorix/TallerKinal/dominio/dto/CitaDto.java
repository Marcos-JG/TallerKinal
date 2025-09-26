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
    // Getters JavaBean para compatibilidad con EL/JSF (variantes con y sin guión bajo)
    public Long getIdCita() { return id_cita; }
    public Long getId_cita() { return id_cita; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalDate getAppointment_date() { return appointmentDate; }

    public Long getIdEmpleado() { return idEmpleado; }
    public Long getId_empleado() { return idEmpleado; }

    public Long getIdCliente() { return idCliente; }
    public Long getId_cliente() { return idCliente; }

    public String getAppointmentType() { return appointmentType; }
    public String getAppointment_type() { return appointmentType; }

    public Long getIdVehiculo() { return idVehiculo; }
    public Long getId_vehiculo() { return idVehiculo; }

    public String getStatus() { return status; }

    public String getComments() { return comments; }
    public String getComentarios() { return comments; }
}
