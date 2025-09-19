package org.Algorix.TallerKinal.dominio.exception;

public class ClienteNoExiste extends RuntimeException {
    public ClienteNoExiste(Long id) {
        super("El cliente con id: = " + id + " no existe en el taller");
    }
}
