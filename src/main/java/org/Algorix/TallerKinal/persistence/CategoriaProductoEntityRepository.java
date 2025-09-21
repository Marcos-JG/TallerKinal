package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.dominio.exception.CategoriaNoExiste;
import org.Algorix.TallerKinal.dominio.repository.CategoriaRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudCategoria;
import org.Algorix.TallerKinal.persistence.entity.CategoriaProductoEntity;
import org.Algorix.TallerKinal.web.mapper.CategoriaProductoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaProductoEntityRepository implements CategoriaRepository {
    private final CrudCategoria crudCategoriaProducto;
    private final CategoriaProductoMapper categoriaProductoMapper;

    public CategoriaProductoEntityRepository(CrudCategoria crudCategoriaProducto, CategoriaProductoMapper categoriaProductoMapper) {
        this.crudCategoriaProducto = crudCategoriaProducto;
        this.categoriaProductoMapper = categoriaProductoMapper;
    }

    @Override
    public List<CategoriaProductoDto> obtenerTodo() {
        return this.categoriaProductoMapper.toDto(this.crudCategoriaProducto.findAll());
    }

    @Override
    public CategoriaProductoDto obtenerCategoriaPorId(Long codigo) {
        CategoriaProductoEntity categoriaEntity = this.crudCategoriaProducto.findById(codigo).orElse(null);
        if (categoriaEntity == null) {
            throw new CategoriaNoExiste(codigo);
        }
        return this.categoriaProductoMapper.toDto(categoriaEntity);
    }

    @Override
    public void eliminarCategoria(Long codigo) {
        CategoriaProductoEntity categoriaEntity = this.crudCategoriaProducto.findById(codigo).orElse(null);
        if (categoriaEntity == null) {
            throw new CategoriaNoExiste(codigo);
        }
        this.crudCategoriaProducto.deleteById(codigo);
    }

    @Override
    public CategoriaProductoDto modificarCategoria(Long codigo, ModCategoriaDto modCategoriaDto) {
        CategoriaProductoEntity categoriaEntity = this.crudCategoriaProducto.findById(codigo).orElse(null);
        if (categoriaEntity == null) {
            throw new CategoriaNoExiste(codigo);
        }
        this.categoriaProductoMapper.modificarEntityFromDto(modCategoriaDto, categoriaEntity);
        return this.categoriaProductoMapper.toDto(this.crudCategoriaProducto.save(categoriaEntity));
    }

    @Override
    public CategoriaProductoDto guardarCategoria(CategoriaProductoDto categoriaProductoDto) {
        CategoriaProductoEntity categoria = this.categoriaProductoMapper.toEntity(categoriaProductoDto);
        this.crudCategoriaProducto.save(categoria);
        return this.categoriaProductoMapper.toDto(categoria);
    }
}
