package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class MarcaProductoDto {
    public Long id_marca;

    @NotBlank(message = "El nombre no puede estar vacio")
    public String nombre;
}
