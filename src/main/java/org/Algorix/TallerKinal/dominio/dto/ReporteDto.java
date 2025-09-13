package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

public class ReporteDto {
    public Long id;

    @Min(value = 1, message = "El id de la cita debe ser válido")
    public Long idCita;

    @NotBlank(message = "La descripción general no puede estar vacia")
    public String descripcionGeneral;

    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser negativo")
    public BigDecimal total;
}
