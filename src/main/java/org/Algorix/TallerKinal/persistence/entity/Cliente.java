package org.Algorix.TallerKinal.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_cliente;

    public String nombre;
    public String apellido;
    public String correo;
    public String contraseña;
}
