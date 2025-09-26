package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ModReporteDto(
        @Min(value = 0, message = "El id de la cita debe ser un valor positivo")
        Long idCita,
        @NotBlank(message = "La descripcion del reporte no puede estar vacia")
        String description,
        @Min(value = 0, message = "El total del reporte debe ser mayor o igual MechanicView 0")
        Double total
) {
}
