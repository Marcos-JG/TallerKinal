package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record MarcaProductoDto (
        Long id_marca,

        @NotBlank(message = "El nombre no puede estar vacio")
        String name
){
    // Getters JavaBean para compatibilidad con EL/JSF
    public Long getIdMarca() { return id_marca; }
    public Long getId_marca() { return id_marca; }

    public String getName() { return name; }
    public String getNombre() { return name; }
}
