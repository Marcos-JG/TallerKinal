package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "vehiculos")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public ClienteEntity cliente;

    public String placas;
    public String color;
    public String modelo;
    public String marca;
    public Integer año;
}

