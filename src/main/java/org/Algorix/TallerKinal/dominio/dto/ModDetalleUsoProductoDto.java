package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record ModDetalleUsoProductoDto(
        Long idTrabajoRealizado,
        Long idProductoInventario,
        @Min(message = "La cantidad utilizada debe ser mayor o igual MechanicView 1", value = 1)
        Integer usedQuantity,
        @Min(message = "El precio unitario debe ser mayor o igual MechanicView 0", value = 0)
        BigDecimal unitPrice,
        @Min(message = "El subtotal debe ser mayor o igual MechanicView 0", value = 0)
        BigDecimal subtotal
) {
}
