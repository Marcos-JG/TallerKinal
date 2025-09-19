package org.Algorix.TallerKinal.dominio.exception;

public class AdministradorNoExiste extends RuntimeException {
    public AdministradorNoExiste(long id) {
        super("El administrador con id " + id + " no existe.");
    }
}
