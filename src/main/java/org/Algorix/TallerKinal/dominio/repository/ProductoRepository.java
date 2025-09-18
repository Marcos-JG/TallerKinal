package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import java.util.List;

public interface ProductoRepository {
    List<ProductoInventarioDto> obtenerTodo();
}
