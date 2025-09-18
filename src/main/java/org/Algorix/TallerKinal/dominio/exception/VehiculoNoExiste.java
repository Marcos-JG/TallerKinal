package org.Algorix.TallerKinal.dominio.exception;

public class VehiculoNoExiste extends RuntimeException {
    public VehiculoNoExiste(Long id) {
        super("Vehiculo no encontrado: " + id);
    }
}
