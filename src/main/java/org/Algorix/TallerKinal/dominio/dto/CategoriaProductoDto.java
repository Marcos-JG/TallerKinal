package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaProductoDto (
        Long id_categoria,

        @NotBlank(message = "El nombre no puede estar vacio")
        String name
){
}
