package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ModProductoInventarioDto(
        Long idProveedor,
        @NotBlank(message = "El nombre del producto es obligatorio")
        String name,
        @NotBlank(message = "La descripcion del producto es obligatoria")
        String description,
        Long idCategoria,
        @NotBlank(message = "La especificacion del producto es obligatoria")
        String specification,
        @DecimalMin( value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor que 0")
        Double unitPrice,
        Integer currentStock,
        Integer minimumStock,
        Long idMarca,
        @PastOrPresent(message = "La fecha de entrada debe ser anterior o igual a la fecha actual")
        LocalDate entryDate

) {
}
