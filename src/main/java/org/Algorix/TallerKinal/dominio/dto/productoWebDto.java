package org.Algorix.TallerKinal.dominio.dto;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class productoWebDto {

    private Long idProducto;
    private Long idProveedor;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacia")
    private String description;

    @NotBlank(message = "La categoría no puede estar vacia")
    private Long idCategoria;

    @NotBlank(message = "La especificación no puede estar vacia")
    private String specification;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
    private Double unitPrice;

    @Min(value = 0, message = "El stock actual no puede ser menor MechanicView 0")
    private Integer currentStock;

    @Min(value = 0, message = "El stock mínimo no puede ser menor MechanicView 0")
    private Integer minimumStock;

    private Long idMarca;

    @PastOrPresent(message = "La fecha de entrada no puede ser futura")
    private LocalDate entryDate;
}

