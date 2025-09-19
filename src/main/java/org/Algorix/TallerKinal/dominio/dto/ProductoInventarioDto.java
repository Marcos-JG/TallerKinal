package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record ProductoInventarioDto(
     Long id_producto,
     Long idProveedor,

    @NotBlank(message = "El nombre no puede estar vacio")
     String name,
    @NotBlank(message = "La descripción no puede estar vacia")
     String description,
     @NotBlank(message = "La categoría no puede estar vacia")
     Long idCategoria,
    @NotBlank(message = "La especificación no puede estar vacia")
     String specification,
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
     Double unitPrice,
    @Min(value = 0, message = "El stock actual no puede ser menor a 0")
     Integer currentStock,
    @Min(value = 0, message = "El stock mínimo no puede ser menor a 0")
     Integer minimumStock,
     Long idMarca,
    @PastOrPresent(message = "La fecha de entrada no puede ser futura")
     LocalDate entryDate
    ){
}
