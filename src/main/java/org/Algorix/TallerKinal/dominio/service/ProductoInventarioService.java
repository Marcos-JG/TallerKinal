package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoInventarioService {
    private final ProductoRepository productoRepository;

    public ProductoInventarioService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public List<ProductoInventarioDto> obtenerTodo() {
        return this.productoRepository.obtenerTodo();
    }
}
