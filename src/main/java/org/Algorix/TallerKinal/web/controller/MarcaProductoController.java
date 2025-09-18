package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> obtenerMarcaPorCodigo(
            @Parameter(description = "Identificador de la pelicula a recuperar",example = "5")
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.marcaProductoService.obtenerMarcaPorCodigo(codigo));
    }
}

