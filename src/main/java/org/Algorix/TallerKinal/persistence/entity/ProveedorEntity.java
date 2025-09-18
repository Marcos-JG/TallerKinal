package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_proveedor;

    public String nombreEmpresa;
    public String contacto;
    public String telefono;
    public String correo;
}
