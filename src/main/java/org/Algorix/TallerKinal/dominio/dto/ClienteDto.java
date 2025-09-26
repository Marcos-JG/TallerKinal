package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long id_cliente;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;

    @NotBlank(message = "El correo no puede estar vacio")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;
}
