package org.Algorix.TallerKinal.dominio.dto;

import org.hibernate.validator.constraints.NotBlank;

public record UserClienteDto (
        @NotBlank (message = "El correo no puede estar vacio")
        String email,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){
}
