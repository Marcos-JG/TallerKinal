package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.service.ProductoInventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/productos-inventario")
public class ProductoInventarioController {
    private final ProductoInventarioService productoInventarioService;

    @Autowired
    public ProductoInventarioController(ProductoInventarioService productoInventarioService) {
        this.productoInventarioService = productoInventarioService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoInventarioDto>> listarInventario() {
        return ResponseEntity.ok(this.productoInventarioService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ProductoInventarioDto> obtenerProductoPorCodigo(
            @Parameter(description = "Identificador de la pelicula a recuperar",example = "5")
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.productoInventarioService.obtenerProductoPorCodigo(codigo));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<ProductoInventarioDto> eliminarProducto(@PathVariable Long codigo){
        this.productoInventarioService.eliminarProducto(codigo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{codigo}")
    public ResponseEntity<ProductoInventarioDto> modificarProducto
            (@PathVariable Long codigo, @RequestBody @Valid ModProductoInventarioDto modProductoInventarioDto){
        return ResponseEntity.ok(this.productoInventarioService.modificarProducto(codigo,modProductoInventarioDto));
    }

    @PostMapping
    public ResponseEntity<ProductoInventarioDto> guardarProducto(@RequestBody ProductoInventarioDto productoInventarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoInventarioService.guardarProducto(productoInventarioDto));
    }

}

