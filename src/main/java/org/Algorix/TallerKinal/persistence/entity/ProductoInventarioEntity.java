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
    private Long idProducto;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private ProveedorEntity proveedor;

    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private CategoriaProductoEntity categoria;

    @Column(length = 100)
    private String especificacion;
    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    @Column
    private Integer stockActual;
    @Column
    private Integer stockMinimo;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private MarcaProductoEntity marca;

    @Column
    private LocalDate fechaEntrada;
}
