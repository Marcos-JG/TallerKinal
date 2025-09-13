package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_uso_producto")
public class DetalleUsoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_detalle_uso_producto;

    @ManyToOne
    @JoinColumn(name = "id_trabajo_realizado")
    public TrabajoRealizado trabajoRealizado;

    @ManyToOne
    @JoinColumn(name = "id_producto_inventario")
    public ProductoInventario productoInventario;

    public Integer cantidadUsada;
    public BigDecimal precioUnitario;
    public BigDecimal subtotal;
}

