package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.Algorix.TallerKinal.dominio.MarcaVehiculo;

public record ModVehiculoDto (
        @NotBlank(message = "El color no puede estar vacio")
        String color,

        @NotBlank(message = "El modelo no puede estar vacio")
        String model,

        @NotNull(message = "La marca no puede estar vacia")
        MarcaVehiculo marca,

        @Min(value = 1886, message = "El año no puede ser menor MechanicView 1886")
        @Max(value = 2100, message = "El año no puede ser mayor MechanicView 2100")
        Integer year,

        Long idCliente
){

}
