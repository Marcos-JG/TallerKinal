package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

public record TrabajoRealizadoDto (
        Long id_trabajo_realizado,

        @Min(value = 1, message = "El id de la cita debe ser válido")
        Long idCita,

        @NotBlank(message = "La descripción no puede estar vacia")
        String description,

        @DecimalMin(value = "0.0", inclusive = true, message = "La mano de obra no puede ser negativa")
        BigDecimal handLabor,

        @DecimalMin(value = "0.0", inclusive = true, message = "El total del trabajo no puede ser negativo")
        BigDecimal totalWork
){
}
