package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.MarcaProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudMarcaProducto;
import org.Algorix.TallerKinal.persistence.crud.CrudProducto;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.Algorix.TallerKinal.web.mapper.MarcaProductoMapper;
import org.Algorix.TallerKinal.web.mapper.ProductoInventarioMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarcaProductoEntityRepository implements MarcaProductoRepository {
    private final CrudMarcaProducto crudMarcaProducto;
    private final MarcaProductoMapper marcaProductoMapper;

    //Inyeccion de dependencias implicito sin el @Autowired
    public MarcaProductoEntityRepository(CrudMarcaProducto crudMarcaProducto, MarcaProductoMapper marcaProductoMapper) {
        this.crudMarcaProducto = crudMarcaProducto;
        this.marcaProductoMapper = marcaProductoMapper;
    }

    @Override
    public List<MarcaProductoDto> obtenerTodo() {
        return this.marcaProductoMapper.toDto(this.crudMarcaProducto.findAll());
    }

    @Override
    public MarcaProductoDto obtenerMarcaPorCodigo(Long codigo) {
        MarcaProductoEntity marcaEntity = this.crudMarcaProducto.findById(codigo).orElse(null);
        return marcaEntity != null ? this.marcaProductoMapper.toDto(marcaEntity) : null;
    }
}
