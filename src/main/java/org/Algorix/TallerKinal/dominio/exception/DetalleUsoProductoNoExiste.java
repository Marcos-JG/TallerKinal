package org.Algorix.TallerKinal.dominio.exception;

public class DetalleUsoProductoNoExiste extends RuntimeException {
  public DetalleUsoProductoNoExiste(Long id) {
    super("El detalle de uso del producto no existe." );
  }
}
