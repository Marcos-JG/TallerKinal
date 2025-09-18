package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "productos_inventario")
public class ProductoInventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaProductoEntity categoria;

    private String especificacion;
    private BigDecimal precioUnitario;
    private Integer stockActual;
    private Integer stockMinimo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaProductoEntity marca;

    private LocalDate fechaEntrada;
}

