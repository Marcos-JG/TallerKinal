package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "detalle_uso_producto")
public class DetalleUsoProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_uso_producto;

    @ManyToOne
    @JoinColumn(name = "id_trabajo_realizado")
    private TrabajoRealizadoEntity trabajoRealizado;

    @ManyToOne
    @JoinColumn(name = "id_producto_inventario")
    private ProductoInventarioEntity productoInventario;

    @Column
    private Integer cantidadUsada;
    @Column
    private BigDecimal precioUnitario;
    @Column
    private BigDecimal subtotal;
}
