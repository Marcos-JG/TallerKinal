package org.Algorix.TallerKinal.dominio.exception;

public class CategoriaNoExiste extends RuntimeException {
    public CategoriaNoExiste(Long id) {
        super("Categoria no encontrada: " + id);
    }
}
