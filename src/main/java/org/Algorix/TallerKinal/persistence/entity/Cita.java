package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_cita;

    public LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    public Mecanico empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente cliente;

    public String tipoCita;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    public Vehiculo vehiculo;

    public String estadoCita;

    public String comentario;
}

