package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mecanicos")
public class Mecanico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_mecanico;

    public String nombre;
    public String apellido;
    public String telefono;
}

