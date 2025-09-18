package org.Algorix.TallerKinal.dominio.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class AdministradorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_admin;

    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    String apellido;

    @NotBlank(message = "El correo no puede estar vacio")
    String correo;

    @NotBlank(message = "La contraseña no puede estar vacia")
    String contraseña;

    @NotBlank(message = "El teléfono no puede estar vacio")
    String telefono;
}
