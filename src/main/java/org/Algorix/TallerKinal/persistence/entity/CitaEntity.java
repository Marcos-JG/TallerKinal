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

    @Column
    public LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    public MecanicoEntity empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public ClienteEntity cliente;

    @Column(length = 50)
    public String tipoCita;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    public VehiculoEntity vehiculo;

    @Column(length = 30)
    public String estadoCita;

    @Column(length = 255)
    public String comentario;
}
