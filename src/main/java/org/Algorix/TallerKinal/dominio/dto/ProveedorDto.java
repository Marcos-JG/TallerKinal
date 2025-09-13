package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class ProveedorDto {
    public Long id;

    @NotBlank(message = "El nombre de la empresa no puede estar vacio")
    public String nombreEmpresa;

    @NotBlank(message = "El contacto no puede estar vacio")
    public String contacto;

    @NotBlank(message = "El teléfono no puede estar vacio")
    public String telefono;

    @NotBlank(message = "El correo no puede estar vacio")
    public String correo;
}
