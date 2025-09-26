package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mecanicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre","apellido","telefono"})})
public class MecanicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mecanico;

    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String apellido;
    @Column(length = 20)
    private String telefono;
    // Campo único derivado para prevenir duplicados normalizados (nombre+apellido+telefono)
    @Column(name = "unique_key", length = 255, unique = true)
    private String uniqueKey;

    @PrePersist
    @PreUpdate
    private void normalizeAndSetUniqueKey() {
        if (this.nombre != null) this.nombre = this.nombre.trim();
        if (this.apellido != null) this.apellido = this.apellido.trim();
        if (this.telefono != null) this.telefono = this.telefono.trim();
        String n = this.nombre == null ? "" : this.nombre.toLowerCase();
        String ln = this.apellido == null ? "" : this.apellido.toLowerCase();
        String ph = this.telefono == null ? "" : this.telefono.toLowerCase();
        this.uniqueKey = n + "|" + ln + "|" + ph;
    }
}
