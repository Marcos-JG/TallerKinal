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

    private String descripcion;
    private BigDecimal manoObra;
    private BigDecimal totalTrabajo;
}

