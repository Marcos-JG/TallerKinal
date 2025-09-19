package org.Algorix.TallerKinal.dominio.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public record AdministradorDto(
        Long id_admin,

        @NotBlank(message = "El nombre no puede estar vacio")
        String name,

        @NotBlank(message = "El apellido no puede estar vacio")
        String lastname,

        @NotBlank(message = "El correo no puede estar vacio")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacia")
        String password,

        @NotBlank(message = "El teléfono no puede estar vacio")
        String phone
) {

}
