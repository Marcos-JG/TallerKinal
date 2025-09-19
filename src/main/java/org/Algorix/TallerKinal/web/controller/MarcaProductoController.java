package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/marcas-producto")
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

    @PostMapping
    public ResponseEntity<MarcaProductoDto> guardarMarca(@RequestBody MarcaProductoDto marcaProductoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.marcaProductoService.guardarMarca(marcaProductoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> modificarMarca
            (@PathVariable Long codigo, @RequestBody @Valid ModMarcaProductoDto modMarcaProductoDto){
        return ResponseEntity.ok(this.marcaProductoService.modificarMarca(codigo,modMarcaProductoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> eliminarMarca(@PathVariable Long codigo){
        this.marcaProductoService.eliminarMarca(codigo);
        return ResponseEntity.ok().build();
    }

}

