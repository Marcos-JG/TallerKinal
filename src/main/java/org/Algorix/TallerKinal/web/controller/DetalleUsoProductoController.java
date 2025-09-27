package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.*;
import org.Algorix.TallerKinal.dominio.repository.DetalleUsoProductoRepository;
import org.Algorix.TallerKinal.dominio.service.DetalleUsoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detalleusoproductos")
@Tag(name = "DetalleUsoProducto", description = "Operaciones sobre el detalle del uso de productos")
public class DetalleUsoProductoController {
    private final DetalleUsoProductoService detalleUsoProductoService;

    public DetalleUsoProductoController(DetalleUsoProductoService detalleUsoProductoService) {
        this.detalleUsoProductoService = detalleUsoProductoService;
    }


    @GetMapping
    @Operation(summary = "Listar Detalles",
            responses = {@ApiResponse(responseCode = "201", description = "Operación exitosa")})
    public ResponseEntity<List<DetalleUsoProductoDto>> obtenerDetalles() {
        return ResponseEntity.ok(detalleUsoProductoService.obtenerTodo());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar detalle por su identificador",
            description = "Retorna un detalle en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "detalle no encontrado", content = @Content)
            }
    )
    public ResponseEntity<DetalleUsoProductoDto> obtenerDetallePorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.detalleUsoProductoService.obtenerDetallePorCodigo(id));
    }

    @PostMapping
    @Operation(summary = "Crear Detalle",
            responses = {@ApiResponse(responseCode = "201", description = "Detalle creado")})
    public  ResponseEntity<DetalleUsoProductoDto> agregarDetalle(@RequestBody @Valid DetalleUsoProductoDto detalleUsoProductoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detalleUsoProductoService.guardarDetalle(detalleUsoProductoDto));
    }

    @PutMapping("{id}")
    @Operation(summary = "Buscar Detalle por su identificador",
            responses = {@ApiResponse(responseCode = "201", description = "Detalle modificado")})
    public ResponseEntity<DetalleUsoProductoDto> modificarDetalle(@PathVariable Long id, @RequestBody @Valid ModDetalleUsoProductoDto modDetalleUsoProductoDto) {
        return ResponseEntity.ok(this.detalleUsoProductoService.modificarDetalle(id, modDetalleUsoProductoDto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Detalle por su identificador",
            responses = {@ApiResponse(responseCode = "201", description = "Detalle eliminado")})
    public ResponseEntity<DetalleUsoProductoDto> eliminarDetalle(@PathVariable Long id) {
        this.detalleUsoProductoService.eliminarDetalle(id);
        return ResponseEntity.ok().build();
    }
}
