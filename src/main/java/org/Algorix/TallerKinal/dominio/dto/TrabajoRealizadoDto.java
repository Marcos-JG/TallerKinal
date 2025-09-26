package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

public record TrabajoRealizadoDto (
        Long id_trabajo,

        @Min(value = 1, message = "El id de la cita debe ser válido")
        Long idCita,
        @NotBlank(message = "La descripción no puede estar vacia")
        String description,
        @DecimalMin(value = "0.0", inclusive = true, message = "La mano de obra no puede ser negativa")
        BigDecimal laborCost,
        @DecimalMin(value = "0.0", inclusive = true, message = "El total del trabajo no puede ser negativo")
        BigDecimal totalWork
){
    // Getters JavaBean para compatibilidad con EL/JSF
    public Long getId_trabajo() { return id_trabajo; }
    public Long getIdTrabajo() { return id_trabajo; }

    public Long getIdCita() { return idCita; }
    public Long getId_cita() { return idCita; }

    public String getDescription() { return description; }

    public BigDecimal getLaborCost() { return laborCost; }

    public BigDecimal getTotalWork() { return totalWork; }
}
