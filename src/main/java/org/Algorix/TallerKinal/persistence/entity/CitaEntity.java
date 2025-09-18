package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "citas")
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_cita;

    public LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    public MecanicoEntity empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public ClienteEntity cliente;

    public String tipoCita;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    public VehiculoEntity vehiculo;

    public String estadoCita;

    public String comentario;
}

