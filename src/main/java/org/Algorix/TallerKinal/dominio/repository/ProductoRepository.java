package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductoRepository {

    List<ProductoInventarioDto> obtenerTodo();
    ProductoInventarioDto obtenerProductoPorCodigo(Long codigo);
    void eliminarProducto(Long codigo);
    ProductoInventarioDto modificarProducto(Long codigo, ModProductoInventarioDto modProductoInventario);
    ProductoInventarioDto guardarProducto(ProductoInventarioDto productoInventarioDto);
}
