package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ProductoInventarioDto {
     Long id_producto;
     Long idProveedor;

    @NotBlank(message = "El nombre no puede estar vacio")
     String nombre;

    @NotBlank(message = "La descripción no puede estar vacia")
     String descripcion;
     Long idCategoria;

    @NotBlank(message = "La especificación no puede estar vacia")
     String especificacion;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
     BigDecimal precioUnitario;

    @Min(value = 0, message = "El stock actual no puede ser menor a 0")
     Integer stockActual;

    @Min(value = 0, message = "El stock mínimo no puede ser menor a 0")
     Integer stockMinimo;
     Long idMarca;

    @PastOrPresent(message = "La fecha de entrada no puede ser futura")
     LocalDate fechaEntrada;
}
