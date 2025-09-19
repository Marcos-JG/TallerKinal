package org.Algorix.TallerKinal.dominio.exception;

public class ContrasenaInvalida extends RuntimeException {
    public ContrasenaInvalida(String contrasena) {
        super("La contrasena no cumple con los requisitos minimos: " + contrasena);
    }
}
