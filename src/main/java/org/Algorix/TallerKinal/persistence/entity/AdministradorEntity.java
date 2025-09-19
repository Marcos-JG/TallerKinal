package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "administradores")
public class AdministradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_admin;
    @Column(length = 100)
    public String nombre;
    @Column(length = 100)
    public String apellido;
    @Column(length = 100, unique = true)
    public String correo;
    @Column(length = 100)
    public String contrasena;
    @Column(length = 8, unique = true)
    public String telefono;
}

