package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record UserAdminDto (
        @NotBlank (message = "El correo no puede estar vacio")
        String email,

        @NotBlank (message = "La contraseña no puede estar vacia")
        String password
){
}
