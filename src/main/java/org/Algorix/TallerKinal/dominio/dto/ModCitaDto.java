package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public record ModCitaDto(
        Long idEmpleado,
        @FutureOrPresent(message = "la fecha no puede ser pasada")
        LocalDate appointmentDate,
        String appointmentType,
        Long idVehiculo,
        String status,
        String comments
) {
}
