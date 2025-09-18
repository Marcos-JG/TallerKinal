package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "administradores")
public class AdministradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String apellido;
    @Column(length = 100, unique = true)
    private String correo;
    @Column(length = 100)
    private String contrasena;
    @Column(length = 8, unique = true)
    private String telefono;
}

