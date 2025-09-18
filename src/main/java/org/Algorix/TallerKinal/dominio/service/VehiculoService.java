package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.repository.VehiculoRepository;

import java.util.List;

public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<VehiculoDto> VehiculoRepository() {
        return this.vehiculoRepository.obtenerTodo();
    }
}
