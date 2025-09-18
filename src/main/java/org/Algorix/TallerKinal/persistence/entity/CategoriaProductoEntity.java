package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias_producto")
public class CategoriaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nombre;
}

