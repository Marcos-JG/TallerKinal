package org.Algorix.TallerKinal.dominio.exception;

public class StockInsuficiente extends RuntimeException {
    public StockInsuficiente(int cantidadSolicitada, int cantidadDisponible) {
        super("Stock insuficiente: Solicitud de " + cantidadSolicitada + " unidades, pero solo hay " + cantidadDisponible + " disponibles.");
    }
}
