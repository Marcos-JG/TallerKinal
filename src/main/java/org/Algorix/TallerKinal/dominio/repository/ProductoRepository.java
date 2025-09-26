package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.productoWebDto;
import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;

import java.util.List;

public interface ProductoRepository {
    List<ProductoInventarioDto> obtenerTodo();
    ProductoInventarioDto obtenerProductoPorCodigo(Long codigo);
    void eliminarProducto(Long codigo);
    ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventario);
    ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto);

    List<productoWebDto> obtenerProductos();
    productoWebDto guardarProductos(productoWebDto productoWebDto);
}
