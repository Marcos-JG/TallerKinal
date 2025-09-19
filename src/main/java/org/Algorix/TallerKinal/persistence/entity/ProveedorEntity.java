package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;

    @Column(length = 100, nullable = false)
    private String nombreEmpresa;
    @Column(length = 100)
    private String contacto;
    @Column(length = 20)
    private String telefono;
    @Column(length = 100)
    private String correo;
}
