package org.Algorix.TallerKinal.dominio.exception;

public class ReporteNoExiste extends RuntimeException {
    public ReporteNoExiste(Long id) {
        super("El reporte con id " + id + " no existe en el sistema.");
    }
}
