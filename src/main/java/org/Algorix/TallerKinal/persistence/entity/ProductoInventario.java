package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos_inventario")
public class ProductoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    public Proveedor proveedor;

    public String nombre;
    public String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    public CategoriaProducto categoria;

    public String especificacion;
    public BigDecimal precioUnitario;
    public Integer stockActual;
    public Integer stockMinimo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    public MarcaProducto marca;

    public LocalDate fechaEntrada;
}

