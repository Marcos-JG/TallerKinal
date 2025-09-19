package org.Algorix.TallerKinal.dominio.exception;

public class MarcaNoExiste extends RuntimeException {
    public MarcaNoExiste(Long id) {
        super("La marca no existe: " + id);
    }
}
