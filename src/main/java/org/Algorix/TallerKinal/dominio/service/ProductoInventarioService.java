package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.productoWebDto;
import org.Algorix.TallerKinal.dominio.repository.DetalleUsoProductoRepository;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.Algorix.TallerKinal.web.mapper.ProductoInventarioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoInventarioService  {

    private final ProductoRepository productoRepository;
    private final DetalleUsoProductoRepository detalleUsoProductoRepository;
    private final ProductoInventarioMapper mapper;

    public ProductoInventarioService(ProductoRepository productoRepository,
                                     DetalleUsoProductoRepository detalleUsoProductoRepository, ProductoInventarioMapper mapper) {
        this.productoRepository = productoRepository;
        this.detalleUsoProductoRepository = detalleUsoProductoRepository;
        this.mapper = mapper;
    }

    public List<ProductoInventarioDto> obtenerTodo() {
        return this.productoRepository.obtenerTodo();
    }

    public ProductoInventarioDto obtenerProductoPorCodigo(Long codigo){
        return this.productoRepository.obtenerProductoPorCodigo(codigo);
    }

    public void eliminarProducto(Long codigo){
        this.productoRepository.eliminarProducto(codigo);
    }

    public ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventarioDto){
        return this.productoRepository.modificarProducto(codigo, modProductoInventarioDto);
    }

    public ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto) {
        return this.productoRepository.guardarProducto(productoInventarioDto);

    }

    public List<productoWebDto> obtenerTodosLosProductos() {
        return this.productoRepository.obtenerProductos();
    }

    public ProductoInventarioDto guardarProductos(ProductoInventarioEntity entity) {
        ProductoInventarioDto dto = mapper.toDto(entity);
        return guardarProducto(dto);
    }
}
