package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.repository.VehiculoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudVehiculo;

import java.util.List;

public class VehiculoEntityRepository implements VehiculoRepository {

    private final CrudVehiculo crudVehiculo;

    public VehiculoEntityRepository(CrudVehiculo crudVehiculo) {
        this.crudVehiculo = crudVehiculo;
    }


}
