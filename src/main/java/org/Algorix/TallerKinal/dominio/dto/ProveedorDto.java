package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public class ProveedorDto {
     Long id_proveedor;

    @NotBlank(message = "El nombre de la empresa no puede estar vacio")
     String nombreEmpresa;

    @NotBlank(message = "El contacto no puede estar vacio")
     String contacto;

    @NotBlank(message = "El teléfono no puede estar vacio")
     String telefono;

    @NotBlank(message = "El correo no puede estar vacio")
     String correo;
}
