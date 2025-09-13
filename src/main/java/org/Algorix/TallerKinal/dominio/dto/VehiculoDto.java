package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class VehiculoDto {
    public Long id_vehiculo;
    public Long idCliente;

    @NotBlank(message = "Las placas no pueden estar vacias")
    public String placas;

    @NotBlank(message = "El color no puede estar vacio")
    public String color;

    @NotBlank(message = "El modelo no puede estar vacio")
    public String modelo;

    @NotBlank(message = "La marca no puede estar vacia")
    public String marca;

    @Min(value = 1886, message = "El año no puede ser menor a 1886")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    public Integer año;
}
