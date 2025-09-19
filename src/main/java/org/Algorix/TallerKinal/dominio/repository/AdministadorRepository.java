package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;

import java.util.List;

public interface AdministadorRepository {
    List<AdministradorDto> obtenerAdministradores();
    AdministradorDto buscarPorId(Long id);
    AdministradorDto iniciarSesion(UserAdminDto userAdminDto);
}
