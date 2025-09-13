package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class MecanicoDto {
    public Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    public String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    public String apellido;

    @NotBlank(message = "El teléfono no puede estar vacio")
    public String telefono;
}
