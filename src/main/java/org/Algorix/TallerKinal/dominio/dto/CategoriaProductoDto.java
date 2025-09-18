package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaProductoDto {
    Long id_categoria;

    @NotBlank(message = "El nombre no puede estar vacio")
    String nombre;
}
