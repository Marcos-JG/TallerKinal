package org.Algorix.TallerKinal.dominio.exception;

public class ProveedorNoExiste extends RuntimeException {
    public ProveedorNoExiste(Long id) {
        super("Proveedor no encontrado: " + id);
    }
}
