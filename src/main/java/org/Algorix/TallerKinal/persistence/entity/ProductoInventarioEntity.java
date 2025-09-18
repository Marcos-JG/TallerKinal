package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos_inventario")
public class ProductoInventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    public ProveedorEntity proveedor;

    public String nombre;
    public String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    public CategoriaProductoEntity categoria;

    public String especificacion;
    public BigDecimal precioUnitario;
    public Integer stockActual;
    public Integer stockMinimo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    public MarcaProductoEntity marca;

    public LocalDate fechaEntrada;
}

