package org.Algorix.TallerKinal.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public String apellido;
    public String correo;
    public String contraseña;
    public String telefono;
}

