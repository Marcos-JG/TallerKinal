package org.Algorix.TallerKinal.dominio.exception;

public class CitaNoExiste extends RuntimeException {
    public CitaNoExiste(Long id) {
        super("La cita con id: = " + id + " no existe en el taller");
    }
}

