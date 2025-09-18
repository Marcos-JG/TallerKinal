package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "reportes")
public class ReporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_reporte;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    public CitaEntity cita;

    public String descripcionGeneral;
    public BigDecimal total;
}

