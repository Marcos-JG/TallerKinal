package org.Algorix.TallerKinal.dominio.exception;

public class AdministradorNoExiste extends RuntimeException {
    public AdministradorNoExiste(long id) {
        super("El administrador con id " + id + " no existe.");
    }
    public AdministradorNoExiste(String email) {
        super("El administrador con correo " + email + " no existe.");
    }
}
