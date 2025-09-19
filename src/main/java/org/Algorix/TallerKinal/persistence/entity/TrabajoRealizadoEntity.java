package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "trabajos_realizados")
public class TrabajoRealizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_trabajo;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private CitaEntity cita;

    @Column(length = 255)
    private String descripcion;
    @Column(precision = 10, scale = 2)
    private BigDecimal manoObra;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalTrabajo;
}
