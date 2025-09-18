package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto (
        Long id_cliente,

        @NotBlank(message = "El nombre no puede estar vacio")
        String name,

        @NotBlank(message = "El apellido no puede estar vacio")
        String lastName,

        @NotBlank(message = "El correo no puede estar vacio")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){
}
