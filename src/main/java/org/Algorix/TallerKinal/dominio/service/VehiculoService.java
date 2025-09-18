package org.Algorix.TallerKinal.dominio.service;


import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }



    public List<VehiculoDto> obtenerTodo() {
        return this.vehiculoRepository.obtenerTodo();
    }

    public VehiculoDto buscarPorPlaca(String placa) {
        return this.vehiculoRepository.buscarPorPlaca(placa);
    }

    public VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto) {
        return this.vehiculoRepository.guardarVehiculo(vehiculoDto);

    }
    public VehiculoDto modificarVehiculo(String placas, ModVehiculoDto vehiculoDto) {
        return this.vehiculoRepository.modificarVehiculo(placas, vehiculoDto);
    }
    public void eliminarVehiculo(Long id) {
        this.vehiculoRepository.eliminarVehiculo(id);
    }
}
