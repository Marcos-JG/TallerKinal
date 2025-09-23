package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.exception.MarcaVehiculoNoExiste;
import org.Algorix.TallerKinal.dominio.exception.VehiculoDuplicadoPlaca;
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
        VehiculoEntity entity = this.crudVehiculo.findFirstByPlacas(placa);
        if (entity == null) {
            throw new VehiculoNoExiste(placa);
        }
        return this.vehiculoMapper.toDto(entity);
    }

    @Override
    public VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto) {
        if (vehiculoDto.marca() == null) {
            throw new MarcaVehiculoNoExiste();
        }
        if (this.crudVehiculo.findFirstByPlacas(vehiculoDto.licensePlate()) != null) {
            throw new VehiculoDuplicadoPlaca(vehiculoDto.licensePlate());
        }
        VehiculoEntity vehiculo = this.vehiculoMapper.toEntity(vehiculoDto);
        this.crudVehiculo.save(vehiculo);
        return this.vehiculoMapper.toDto(vehiculo);
    }



    @Override
    public VehiculoDto modificarVehiculo(String placas, ModVehiculoDto vehiculoDto) {
        VehiculoEntity vehiculo = this.crudVehiculo.findFirstByPlacas(placas);
        if (vehiculo == null) {
            throw new VehiculoNoExiste(placas);
        }
        if (vehiculoDto.marca() == null) {
            throw new MarcaVehiculoNoExiste();
        }
        this.vehiculoMapper.modificarEntityFromDto(vehiculoDto, vehiculo);
        return  this.vehiculoMapper.toDto(this.crudVehiculo.save(vehiculo));
    }

    @Override
    public void eliminarVehiculo(Long id) {
        if (!this.crudVehiculo.existsById(id)) {
            throw new VehiculoNoExiste(id);
        }
        this.crudVehiculo.deleteById(id);
    }
}
