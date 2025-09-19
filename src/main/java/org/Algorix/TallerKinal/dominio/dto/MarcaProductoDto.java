package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record MarcaProductoDto(
    Long id_marca,

    @NotBlank(message = "El nombre no puede estar vacio")
    String name
)
{
}
