package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record MecanicoDto (
    Long idMecanico,
    @NotBlank(message = "El nombre no puede estar vacio")
    String name,
    @NotBlank(message = "El apellido no puede estar vacio")
    String lastName,
    @NotBlank(message = "El teléfono no puede estar vacio")
    String phone
){
    // Agrego getters JavaBean para que EL (BeanELResolver) pueda resolver propiedades como getIdMecanico()
    public Long getIdMecanico() { return idMecanico; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
}
