package org.Algorix.TallerKinal.dominio.exception;

public class TrabajoNoExiste extends RuntimeException {
    public TrabajoNoExiste(Long id) {
        super("Trabajo no encontrado: " + id);
    }
}

