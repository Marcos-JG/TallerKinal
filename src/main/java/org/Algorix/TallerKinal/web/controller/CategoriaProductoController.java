package org.Algorix.TallerKinal.web.controller;

import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.dominio.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categorias-producto")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    @Autowired
    public CategoriaProductoController(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProductoDto>> listarCategorias() {
        return ResponseEntity.ok(this.categoriaProductoService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    public ResponseEntity<CategoriaProductoDto> obtenerCategoriaPorCodigo(
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.categoriaProductoService.obtenerCategoriaPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<CategoriaProductoDto> guardarMarca(@RequestBody CategoriaProductoDto categoriaProductoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body
                (this.categoriaProductoService.guardarCategoria(categoriaProductoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<CategoriaProductoDto> modificarCategoria
            (@PathVariable Long codigo, @RequestBody @Valid ModCategoriaDto modCategoriaDto){
        return ResponseEntity.ok(this.categoriaProductoService.modificarCategoria(codigo,modCategoriaDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<CategoriaProductoDto> eliminarCategoria(@PathVariable Long codigo){
        this.categoriaProductoService.eliminarCategoria(codigo);
        return ResponseEntity.ok().build();
    }
}
