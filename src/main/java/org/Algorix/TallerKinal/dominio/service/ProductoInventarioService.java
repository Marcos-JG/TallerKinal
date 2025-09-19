package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.DetalleUsoProductoRepository;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoInventarioService  {

    private final ProductoRepository productoRepository;
    private final DetalleUsoProductoRepository detalleUsoProductoRepository;

    public ProductoInventarioService(ProductoRepository productoRepository,
                                     DetalleUsoProductoRepository detalleUsoProductoRepository) {
        this.productoRepository = productoRepository;
        this.detalleUsoProductoRepository = detalleUsoProductoRepository;
    }

    public List<ProductoInventarioDto> obtenerTodo() {
        return this.productoRepository.obtenerTodo();
    }

    public ProductoInventarioDto obtenerProductoPorCodigo(Long codigo){
        return this.productoRepository.obtenerProductoPorCodigo(codigo);
    }

    @Transactional
    public void eliminarProducto(Long codigo) {
        detalleUsoProductoRepository.deleteByProductoInventarioId(codigo);
        this.productoRepository.eliminarProducto(codigo);
    }

    public ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventarioDto){
        return this.productoRepository.modificarProducto(codigo, modProductoInventarioDto);
    }

    public ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto) {
        return this.productoRepository.guardarProducto(productoInventarioDto);

    }
}
