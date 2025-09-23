package org.Algorix.TallerKinal.dominio.exception;

public class ProductoNoExiste extends RuntimeException {
    public ProductoNoExiste(Long id) {
        super("Producto no encontrado: " + id);
    }
}
