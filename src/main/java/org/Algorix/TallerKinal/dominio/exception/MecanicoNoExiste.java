package org.Algorix.TallerKinal.dominio.exception;

public class MecanicoNoExiste extends RuntimeException {
    public MecanicoNoExiste(Long is) {
        super("Mecanico no encontrado: " + is);
    }
}
