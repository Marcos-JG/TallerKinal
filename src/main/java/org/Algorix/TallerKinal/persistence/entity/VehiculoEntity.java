package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @Column(length = 50, unique = true, nullable = false)
    private String placas;
    @Column(length = 30)
    private String color;
    @Column(length = 50)
    private String modelo;
    @Column(length = 50)
    private String marca;
    @Column
    private Integer ano;
}
