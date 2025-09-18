package org.Algorix.TallerKinal.dominio.exception;

public class CitaCancelada extends RuntimeException {
    public CitaCancelada(Long id) {
        super("Cita con ID " + id + " ha sido cancelada y no puede ser modificada o eliminada.");
    }
}
