package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.MarcaProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudMarcaProducto;
import org.Algorix.TallerKinal.persistence.crud.CrudProducto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarcaProductoEntityRepository implements MarcaProductoRepository {

    @Override
    public List<MarcaProductoDto> obtenerTodo() {
        return List.of();
    }
}
