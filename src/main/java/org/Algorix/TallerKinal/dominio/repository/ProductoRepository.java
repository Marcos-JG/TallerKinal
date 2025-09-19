package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;

import java.util.List;

public interface ProductoRepository {
    List<ProductoInventarioDto> obtenerTodo();
    ProductoInventarioDto obtenerProductoPorCodigo(Long codigo);
    void eliminarProducto(Long codigo);
    ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventario);
    ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto);
}
