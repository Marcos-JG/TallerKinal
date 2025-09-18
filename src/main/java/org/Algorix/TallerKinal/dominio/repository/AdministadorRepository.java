package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;

import java.util.List;

public interface AdministadorRepository {
    List<AdministradorDto> obtenerAdministradores();
}
