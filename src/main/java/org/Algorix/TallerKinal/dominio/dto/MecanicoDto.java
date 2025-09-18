package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class MecanicoDto {
    Long id_mecanico;

    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    String apellido;

    @NotBlank(message = "El teléfono no puede estar vacio")
    String telefono;
}
