package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.dominio.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/proveedores")
@Tag(name = "Proveedores", description = "Operaciones sobre proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDto>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar proveedor por su identificador",
            description = "Retorna un proveedor en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
            }
    )
    public ResponseEntity<ProveedorDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProveedorDto> guardarProveedor(@RequestBody @Valid ProveedorDto proveedorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.guardarProveedor(proveedorDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProveedorDto> modificarProveedor(@PathVariable Long id, @RequestBody @Valid ProveedorDto proveedorDto) {
        return ResponseEntity.ok(proveedorService.modificarProveedor(id, proveedorDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
