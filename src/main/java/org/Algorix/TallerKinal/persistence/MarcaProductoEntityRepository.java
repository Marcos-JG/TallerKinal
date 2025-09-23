package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.exception.MarcaNoExiste;
import org.Algorix.TallerKinal.dominio.repository.MarcaProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudMarcaProducto;
import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.Algorix.TallerKinal.web.mapper.MarcaProductoMapper;
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
        if (marcaEntity == null) {
            throw new MarcaNoExiste(codigo);
        }
        return this.marcaProductoMapper.toDto(marcaEntity);
    }

    @Override
    public void eliminarMarca(Long codigo) {
        MarcaProductoEntity marcaProductoEntity = this.crudMarcaProducto.findById(codigo).orElse(null);
        if (marcaProductoEntity == null) {
            throw new MarcaNoExiste(codigo);
        }
        this.crudMarcaProducto.deleteById(codigo);
    }

    @Override
    public MarcaProductoDto modificarMarca(Long codigo, ModMarcaProductoDto modMarcaProductoDto) {
        MarcaProductoEntity marcaProductoEntity = this.crudMarcaProducto.findById(codigo).orElse(null);
        if (marcaProductoEntity == null) {
            throw new MarcaNoExiste(codigo);
        }
        this.marcaProductoMapper.modificarEntityFromDto(modMarcaProductoDto, marcaProductoEntity);
        return this.marcaProductoMapper.toDto(this.crudMarcaProducto.save(marcaProductoEntity));
    }

    @Override
    public MarcaProductoDto guardarMarca(MarcaProductoDto marcaProductoDto) {
        MarcaProductoEntity marca = this.marcaProductoMapper.toEntity(marcaProductoDto);
        this.crudMarcaProducto.save(marca);
        return this.marcaProductoMapper.toDto(marca);
    }
}
