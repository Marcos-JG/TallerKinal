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
    private Long id_cita;

    private LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private MecanicoEntity empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    private String tipoCita;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private VehiculoEntity vehiculo;

    private String estadoCita;

    private String comentario;
}

