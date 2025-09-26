package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

public record ReporteDto (
        Long id_reporte,
        @Min(value = 1, message = "El id de la cita debe ser válido")
        Long idCita,
        @NotBlank(message = "La descripción general no puede estar vacia")
        String description,
        @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
        BigDecimal total
){
    // Getters JavaBean para compatibilidad con EL/JSF
    public Long getIdReporte() { return id_reporte; }
    public Long getId_reporte() { return id_reporte; }

    public Long getIdCita() { return idCita; }
    public Long getId_cita() { return idCita; }

    public String getDescription() { return description; }

    public BigDecimal getTotal() { return total; }
}
