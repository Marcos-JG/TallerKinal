package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDto {
    Long id_cliente;

    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    String apellido;

    @NotBlank(message = "El correo no puede estar vacio")
    String correo;

    @NotBlank(message = "La contraseña no puede estar vacia")
    String contraseña;
}
