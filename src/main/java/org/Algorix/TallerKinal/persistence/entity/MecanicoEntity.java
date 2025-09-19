package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mecanicos")
public class MecanicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_mecanico;

    public String nombre;
    public String apellido;
    public String telefono;
}

