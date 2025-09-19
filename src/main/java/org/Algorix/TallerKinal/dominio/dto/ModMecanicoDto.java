package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record ModMecanicoDto(
    @NotBlank(message = "El nombre no puede  estar vacio")
    String name,
    @NotBlank(message = "El apellido no puede estar vacio")
    String lastName,
    @NotBlank(message = "El teléfono no puede estar vacio")
    String phone
) {
}
