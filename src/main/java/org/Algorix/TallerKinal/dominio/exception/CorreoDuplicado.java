package org.Algorix.TallerKinal.dominio.exception;

public class CorreoDuplicado extends RuntimeException {
    public CorreoDuplicado(String correo) {
        super("El correo ya está registrado: " + correo);
    }
}
