package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marcas_producto")
public class MarcaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_marca;

    public String nombre;
}

