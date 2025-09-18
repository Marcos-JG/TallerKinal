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
    public Long id_trabajo;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    public CitaEntity cita;

    public String descripcion;
    public BigDecimal manoObra;
    public BigDecimal totalTrabajo;
}

