package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trabajos_realizados")
public class TrabajoRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_trabajo;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    public Cita cita;

    public String descripcion;
    public BigDecimal manoObra;
    public BigDecimal totalTrabajo;
}

