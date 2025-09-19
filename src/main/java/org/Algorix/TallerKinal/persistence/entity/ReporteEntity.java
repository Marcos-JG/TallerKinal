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
    private Long id_reporte;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private CitaEntity cita;

    @Column(length = 255)
    private String descripcionGeneral;
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
}
