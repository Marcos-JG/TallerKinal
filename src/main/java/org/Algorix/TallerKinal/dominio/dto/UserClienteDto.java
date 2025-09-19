package org.Algorix.TallerKinal.dominio.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record UserClienteDto (
        @NotBlank(message = "El correo no puede estar vacio")
        String email,
        @NotBlank(message = "La contraseña no puede estar vacia")
        String password
){
}
