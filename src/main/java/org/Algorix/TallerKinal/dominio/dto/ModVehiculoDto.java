package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record ModVehiculoDto (
        @NotBlank(message = "no puede estar vacio")
        String color,
        Long idCliente
){

}
