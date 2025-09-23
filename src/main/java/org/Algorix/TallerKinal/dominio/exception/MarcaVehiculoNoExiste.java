package org.Algorix.TallerKinal.dominio.exception;

public class MarcaVehiculoNoExiste extends RuntimeException {
    public MarcaVehiculoNoExiste() {
        super("La marca no existe. Las marcas existentes son TOYOTA, HONDA, FORD" +
                ", CHEVROLET, NISSAN, BMW, MERCEDES, VOLKSWAGEN, HYUNDAI.");
    }
}

