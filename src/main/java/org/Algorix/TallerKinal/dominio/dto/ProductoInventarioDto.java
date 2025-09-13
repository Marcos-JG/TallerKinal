package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ProductoInventarioDto {
    public Long id;
    public Long idProveedor;

    @NotBlank(message = "El nombre no puede estar vacio")
    public String nombre;

    @NotBlank(message = "La descripción no puede estar vacia")
    public String descripcion;
    public Long idCategoria;

    @NotBlank(message = "La especificación no puede estar vacia")
    public String especificacion;

    @Min(value = 0, message = "El precio unitario no puede ser negativo")
    public BigDecimal precioUnitario;

    @Min(value = 0, message = "El stock actual no puede ser menor a 0")
    public Integer stockActual;

    @Min(value = 0, message = "El stock mínimo no puede ser menor a 0")
    public Integer stockMinimo;
    public Long idMarca;

    @PastOrPresent(message = "La fecha de entrada no puede ser futura")
    public LocalDate fechaEntrada;
}
