package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/productos-inventarios")
@Tag(name = "Productos Inventario", description = "Operaciones sobre productos en inventario")
public class ProductoInventarioController {
    private final ProductoInventarioService productoInventarioService;

    @Autowired
    public ProductoInventarioController(ProductoInventarioService productoInventarioService) {
        this.productoInventarioService = productoInventarioService;
    }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Retorna todos los productos del inventario",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<List<ProductoInventarioDto>> listarInventario() {
        return ResponseEntity.ok(this.productoInventarioService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Buscar producto por su identificador",
            description = "Retorna un producto en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
            }
    )
    public ResponseEntity<ProductoInventarioDto> obtenerProductoPorCodigo(
            @Parameter(description = "Identificador del producto MechanicView recuperar",example = "5")
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.productoInventarioService.obtenerProductoPorCodigo(codigo));
    }

    @DeleteMapping("{codigo}")
    @Operation(summary = "Eliminar producto",
            responses = {@ApiResponse(responseCode = "200", description = "Producto eliminado")})
    public ResponseEntity<ProductoInventarioDto> eliminarProducto(@PathVariable Long codigo){
        this.productoInventarioService.eliminarProducto(codigo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{codigo}")
    @Operation(summary = "Modificar producto",
            responses = {@ApiResponse(responseCode = "200", description = "Producto modificado")})
    public ResponseEntity<ProductoInventarioDto> modificarProducto
            (@PathVariable Long codigo, @RequestBody @Valid ModProductoInventarioDto modProductoInventarioDto){
        return ResponseEntity.ok(this.productoInventarioService.modificarProducto(codigo,modProductoInventarioDto));
    }

    @PostMapping
    @Operation(summary = "Crear producto",
            responses = {@ApiResponse(responseCode = "201", description = "Producto creado")})
    public ResponseEntity<ProductoInventarioDto> guardarProducto(@RequestBody @Valid ProductoInventarioDto productoInventarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoInventarioService.guardarProducto(productoInventarioDto));
    }

}
