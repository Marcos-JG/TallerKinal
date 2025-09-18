package org.Algorix.TallerKinal.web.controller;

import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.service.ProductoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos-inventario")
public class ProductoInventarioController {
    private final ProductoInventarioService productoInventarioService;

    @Autowired
    public ProductoInventarioController(ProductoInventarioService productoInventarioService) {
        this.productoInventarioService = productoInventarioService;
    }

    @GetMapping
    public List<ProductoInventarioDto> listarInventario() {
        return productoInventarioService.obtenerTodo();
    }
}

