package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/categorias-productos")
@Tag(name = "Categorias", description = "Operaciones sobre categorías de productos")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    @Autowired
    public CategoriaProductoController(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @GetMapping
    @Operation(summary = "Listar categorías", description = "Retorna todas las categorías",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<List<CategoriaProductoDto>> listarCategorias() {
        return ResponseEntity.ok(this.categoriaProductoService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Buscar categoría por su identificador",
            description = "Retorna una categoría en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
            }
    )
    public ResponseEntity<CategoriaProductoDto> obtenerCategoriaPorCodigo(
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.categoriaProductoService.obtenerCategoriaPorCodigo(codigo));
    }

    @PostMapping
    @Operation(summary = "Crear categoría",
            responses = {@ApiResponse(responseCode = "201", description = "Categoría creada")})
    public ResponseEntity<CategoriaProductoDto> guardarMarca(@RequestBody @Valid CategoriaProductoDto categoriaProductoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body
                (this.categoriaProductoService.guardarCategoria(categoriaProductoDto));
    }

    @PutMapping("{codigo}")
    @Operation(summary = "Modificar categoría",
            responses = {@ApiResponse(responseCode = "200", description = "Categoría modificada")})
    public ResponseEntity<CategoriaProductoDto> modificarCategoria
            (@PathVariable Long codigo, @RequestBody @Valid ModCategoriaDto modCategoriaDto){
        return ResponseEntity.ok(this.categoriaProductoService.modificarCategoria(codigo,modCategoriaDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(summary = "Eliminar categoría",
            responses = {@ApiResponse(responseCode = "200", description = "Categoría eliminada")})
    public ResponseEntity<CategoriaProductoDto> eliminarCategoria(@PathVariable Long codigo){
        this.categoriaProductoService.eliminarCategoria(codigo);
        return ResponseEntity.ok().build();
    }
}
