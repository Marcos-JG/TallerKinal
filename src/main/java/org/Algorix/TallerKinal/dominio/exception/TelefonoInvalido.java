package org.Algorix.TallerKinal.dominio.exception;

public class TelefonoInvalido extends RuntimeException {
    public TelefonoInvalido(Integer telefono) {
        super("El numero de teléfono es inválido: " + telefono);
    }
}
