package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.exception.VehiculoNoExiste;
import org.Algorix.TallerKinal.dominio.repository.VehiculoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudVehiculo;
import org.Algorix.TallerKinal.persistence.entity.VehiculoEntity;
import org.Algorix.TallerKinal.web.mapper.VehiculoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehiculoEntityRepository implements VehiculoRepository {

    private final CrudVehiculo crudVehiculo;
    private final VehiculoMapper vehiculoMapper;

    public VehiculoEntityRepository(CrudVehiculo crudVehiculo, VehiculoMapper vehiculoMapper) {
        this.crudVehiculo = crudVehiculo;
        this.vehiculoMapper = vehiculoMapper;
    }


    @Override
    public List<VehiculoDto> obtenerTodo() {
        return this.vehiculoMapper.toDto(this.crudVehiculo.findAll());
    }

    @Override
    public VehiculoDto buscarPorPlaca(String placa) {
        if (this.crudVehiculo.findFirstByPlacas(placa) == null) {
            throw new VehiculoNoExiste(placa);
        }
        return this.vehiculoMapper.toDto(crudVehiculo.findFirstByPlacas(placa));
    }

    @Override
    public VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto) {
        if (this.crudVehiculo.findFirstByPlacas(vehiculoDto.licensePlate()) != null) {
            throw new VehiculoNoExiste(vehiculoDto.licensePlate());
        }
        VehiculoEntity vehiculo = this.vehiculoMapper.toEntity(vehiculoDto);
        this.crudVehiculo.save(vehiculo);
        return this.vehiculoMapper.toDto(vehiculo);
    }



    @Override
    public VehiculoDto modificarVehiculo(String placas, ModVehiculoDto vehiculoDto) {
        VehiculoEntity vehiculo = this.crudVehiculo.findFirstByPlacas(placas);
        this.vehiculoMapper.modificarEntityFromDto(vehiculoDto, vehiculo);
        return  this.vehiculoMapper.toDto(this.crudVehiculo.save(vehiculo));
    }

    @Override
    public void eliminarVehiculo(Long id) {
        this.crudVehiculo.deleteById(id);
    }
}
