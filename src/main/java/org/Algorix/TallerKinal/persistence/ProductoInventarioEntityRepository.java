package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudProducto;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.Algorix.TallerKinal.web.mapper.ProductoInventarioMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ProductoInventarioEntityRepository implements ProductoRepository {

    private final CrudProducto crudProducto;
    private final ProductoInventarioMapper productoInventarioMapper;

    //Inyeccion de dependencias implicito sin el @Autowired
    public ProductoInventarioEntityRepository(CrudProducto crudProducto, ProductoInventarioMapper productoInventarioMapper) {
        this.crudProducto = crudProducto;
        this.productoInventarioMapper = productoInventarioMapper;
    }

    @Override
    public List<ProductoInventarioDto> obtenerTodo() {
        return this.productoInventarioMapper.toDto(this.crudProducto.findAll());
    }

    @Override
    public ProductoInventarioDto obtenerProductoPorCodigo(Long codigo) {
        ProductoInventarioEntity productoEntity = this.crudProducto.findById(codigo).orElse(null);
        return productoEntity != null ? this.productoInventarioMapper.toDto(productoEntity) : null;
    }

    @Override
    public void eliminarProducto(Long codigo) {
        ProductoInventarioEntity productoEntity = this.crudProducto.findById(codigo).orElse(null);
        this.crudProducto.deleteById(codigo);
    }

    @Override
    public ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventario) {
        ProductoInventarioEntity producto = this.crudProducto.findById(codigo).orElse(null);
        this.productoInventarioMapper.modificarEntityFromDto(modProductoInventario, producto);
        return this.productoInventarioMapper.toDto(this.crudProducto.save(producto));
    }

    @Override
    public ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto) {
        ProductoInventarioEntity producto = this.productoInventarioMapper.toEntity(productoInventarioDto);
        this.crudProducto.save(producto);
        return this.productoInventarioMapper.toDto(producto);
    }
}
