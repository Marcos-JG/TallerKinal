package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_uso_producto")
public class DetalleUsoProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_detalle_uso_producto;

    @ManyToOne
    @JoinColumn(name = "id_trabajo_realizado")
    public TrabajoRealizadoEntity trabajoRealizado;

    @ManyToOne
    @JoinColumn(name = "id_producto_inventario")
    public ProductoInventarioEntity productoInventario;

    public Integer cantidadUsada;
    public BigDecimal precioUnitario;
    public BigDecimal subtotal;
}

