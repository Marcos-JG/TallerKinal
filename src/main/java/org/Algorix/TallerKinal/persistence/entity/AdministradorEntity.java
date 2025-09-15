package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class AdministradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_admin;

    public String nombre;
    public String apellido;
    public String correo;
    public String contraseña;
    public String telefono;
}

