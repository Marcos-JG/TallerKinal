package org.Algorix.TallerKinal.dominio.service;

import jakarta.validation.constraints.NotBlank;
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
}
