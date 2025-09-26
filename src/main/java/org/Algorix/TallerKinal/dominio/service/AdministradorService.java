package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.dominio.repository.AdministadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    private final AdministadorRepository administadorRepository;

    public AdministradorService(AdministadorRepository administadorRepository) {
        this.administadorRepository = administadorRepository;
    }

    public List<AdministradorDto> obtenerAdministradores() {
        return this.administadorRepository.obtenerAdministradores();
    }

    public  AdministradorDto buscarPorId(Long id){
        return this.administadorRepository.buscarPorId(id);
    }

    public AdministradorDto iniciarSesion(UserAdminDto userAdminDto){
        return this.administadorRepository.iniciarSesion(userAdminDto);
    }

    // compatibility methods used by views
    public List<AdministradorDto> obtenerTodo() {
        return obtenerAdministradores();
    }

    public AdministradorDto guardarAdministrador(AdministradorDto administradorDto) {
        return this.administadorRepository.guardarAdministrador(administradorDto);
    }

    public AdministradorDto modificarAdministrador(Long id, AdministradorDto administradorDto) {
        return this.administadorRepository.modificarAdministrador(id, administradorDto);
    }

    public void eliminarAdministrador(Long id) {
        this.administadorRepository.eliminarAdministrador(id);
    }
}
