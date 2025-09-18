package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marcas_producto")
public class MarcaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;

    private String nombre;
}

