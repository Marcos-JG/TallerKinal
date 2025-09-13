package org.Algorix.TallerKinal.dominio.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;

public class DetalleUsoProductoDto {
    public Long id;

    @Min(value = 1, message = "El id del trabajo realizado debe ser válido")
    public Long idTrabajoRealizado;

    @Min(value = 1, message = "El id del producto inventario debe ser válido")
    public Long idProductoInventario;

    @Min(value = 1, message = "La cantidad usada debe ser al menos 1")
    public Integer cantidadUsada;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
    public BigDecimal precioUnitario;

    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser negativo")
    public BigDecimal subtotal;
}
