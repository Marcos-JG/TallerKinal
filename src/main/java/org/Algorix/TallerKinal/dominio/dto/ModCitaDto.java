package org.Algorix.TallerKinal.dominio.dto;

import java.time.LocalDate;

public record ModCitaDto(
        Long idEmpleado,
        LocalDate appointmentDate,
        String appointmentType,
        Long idVehiculo,
        String status,
        String comments
) {
}
