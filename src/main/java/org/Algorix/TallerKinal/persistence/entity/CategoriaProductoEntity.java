package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias_producto")
public class CategoriaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_categoria;
    @Column(length = 100, unique = true, nullable = false)
    public String nombre;
}
