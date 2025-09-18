package org.Algorix.TallerKinal.web.controller;

import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marcas-producto")
public class MarcaProductoController {
    private final MarcaProductoService marcaProductoService;

    @Autowired
    public MarcaProductoController(MarcaProductoService marcaProductoService) {
        this.marcaProductoService = marcaProductoService;
    }

    @GetMapping
    public List<MarcaProductoDto> listarMarcas() {
        return marcaProductoService.obtenerTodo();
    }
}

