package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "categorias_producto")
public class CategoriaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_categoria;

    public String nombre;
}

