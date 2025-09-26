package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaProductoDto (
        Long id_categoria,

        @NotBlank(message = "El nombre no puede estar vacio")
        String name
){
    // Getters JavaBean para compatibilidad con EL/JSF
    public Long getIdCategoria() { return id_categoria; }
    public Long getId_categoria() { return id_categoria; }

    public String getName() { return name; }
    public String getNombre() { return name; }
}
