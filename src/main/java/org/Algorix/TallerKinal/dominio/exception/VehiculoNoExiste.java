package org.Algorix.TallerKinal.dominio.exception;

public class VehiculoNoExiste extends RuntimeException {
    public VehiculoNoExiste(Long id) {
        super("Vehiculo no encontrado: " + id);
    }

    public VehiculoNoExiste(String placa) {
        super("Vehiculo no encontrado con placa: " + placa);
    }
}
