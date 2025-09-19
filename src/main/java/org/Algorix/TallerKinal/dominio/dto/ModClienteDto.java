package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;
public record ModClienteDto (
        @NotBlank(message = "El nombre no puede estar vacio")
        String name,
        @NotBlank(message = "El correo no puede estar vacio")
        String email,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){
}
