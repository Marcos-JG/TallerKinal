package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;

public record DetalleUsoProductoDto (
        Long id_detalle_uso_producto,

        @Min(value = 1, message = "El id del trabajo realizado debe ser válido")
        Long idTrabajoRealizado,

        @Min(value = 1, message = "El id del producto inventario debe ser válido")
        Long idProductoInventario,

    @Min(value = 1, message = "La cantidad usada debe ser al menos 1")
    Integer usedQuantity,

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
    BigDecimal unitPrice,

        @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
        BigDecimal subtotal
){
}
