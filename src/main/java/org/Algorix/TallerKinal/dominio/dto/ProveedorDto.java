package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record ProveedorDto (
        Long id_proveedor,
        @NotBlank(message = "El nombre de la empresa no puede estar vacio")
        String companyName,

        @NotBlank(message = "El contacto no puede estar vacio")
        String contact,

        @NotBlank(message = "El teléfono no puede estar vacio")
        String phone,

        @NotBlank(message = "El correo no puede estar vacio")
        String email
){
    public Long getIdProveedor() { return id_proveedor; }
    public Long getId_proveedor() { return id_proveedor; }

    public String getCompanyName() { return companyName; }
    public String getContact() { return contact; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
