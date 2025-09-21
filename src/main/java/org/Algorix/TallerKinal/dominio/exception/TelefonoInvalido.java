package org.Algorix.TallerKinal.dominio.exception;

public class TelefonoInvalido extends RuntimeException {
    public TelefonoInvalido(String telefono) {
        super("El número de teléfono es inválido: " + telefono);
    }
}
