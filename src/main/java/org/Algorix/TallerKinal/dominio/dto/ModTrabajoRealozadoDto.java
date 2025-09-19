package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ModTrabajoRealozadoDto(
        @NotBlank(message = "La descripción no puede estar vacia")
        String description,
        @NotNull(message = "La mano de obra no puede ser nula")
        @DecimalMin(value = "0.0", inclusive = true, message = "La mano de obra no puede ser negativa")
        BigDecimal laborCost,
        @NotNull(message = "El total del trabajo no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = true, message = "La mano de obra no puede ser negativa")
        BigDecimal totalWork
) {
}
