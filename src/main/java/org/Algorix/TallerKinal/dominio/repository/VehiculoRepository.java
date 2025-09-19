package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;

import java.util.List;

public interface VehiculoRepository {
    List<VehiculoDto> obtenerTodo();
    VehiculoDto buscarPorPlaca(String placa);
    VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto);
    VehiculoDto modificarVehiculo(String placas, ModVehiculoDto vehiculoDto);
    void eliminarVehiculo(Long id);
}
