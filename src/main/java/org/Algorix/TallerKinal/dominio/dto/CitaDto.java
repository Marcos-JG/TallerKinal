package org.Algorix.TallerKinal.dominio.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class CitaDto {
    public Long id_cita;

    @PastOrPresent(message = "La fecha de la cita no puede ser futura")
    public LocalDate appointmentDate;

    @Min(value = 1, message = "El id del empleado debe ser válido")
    public Long idEmpleado;

    @Min(value = 1, message = "El id del cliente debe ser válido")
    public Long idCliente;

    @NotBlank(message = "El tipo de cita no puede estar vacio")
    public String appointmentType;

    @Min(value = 1, message = "El id del vehiculo debe ser válido")
    public Long idVehiculo;

    @NotBlank(message = "El estado de la cita no puede estar vacio")
    public String status;

    public String comments;
}
