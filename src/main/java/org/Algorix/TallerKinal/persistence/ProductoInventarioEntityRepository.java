package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoInventarioEntityRepository implements ProductoRepository {


    @Override
    public List<ProductoInventarioDto> obtenerTodo() {
        return List.of();
    }
}
