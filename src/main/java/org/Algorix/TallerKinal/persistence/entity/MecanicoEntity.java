package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mecanicos")
public class MecanicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mecanico;

    private String nombre;
    private String apellido;
    private String telefono;
}

