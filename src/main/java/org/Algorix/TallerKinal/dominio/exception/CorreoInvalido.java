package org.Algorix.TallerKinal.dominio.exception;

public class CorreoInvalido extends RuntimeException {
    public CorreoInvalido(String correo) {
        super("El correo es inválido: " + correo);
    }
}
