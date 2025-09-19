package org.Algorix.TallerKinal.dominio.exception;

public class VehiculoDuplicadoPlaca extends RuntimeException {
    public VehiculoDuplicadoPlaca(String placa) {
        super("El vehiculo con placa: = " + placa + " ya existe");
    }
}
