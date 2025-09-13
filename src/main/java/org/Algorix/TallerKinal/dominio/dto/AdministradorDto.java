package org.Algorix.TallerKinal.dominio.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "administradores")
public class AdministradorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    public String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    public String apellido;

    @NotBlank(message = "El correo no puede estar vacio")
    public String correo;

    @NotBlank(message = "La contraseña no puede estar vacia")
    public String contraseña;

    @NotBlank(message = "El teléfono no puede estar vacio")
    public String telefono;
}
