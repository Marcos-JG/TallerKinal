package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.service.TrabajoRealizadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trabajosRealizados")
@Tag(name = "Trabajos Realizados", description = "Operaciones sobre trabajos realizados")
public class TrabajoRealizadoController {

    private final TrabajoRealizadoService trabajoRealizadoService;

    public TrabajoRealizadoController(TrabajoRealizadoService trabajoRealizadoService) {
        this.trabajoRealizadoService = trabajoRealizadoService;
    }

    @GetMapping
    public ResponseEntity<List<TrabajoRealizadoDto>> listarTrabajoRealizados() {
        return ResponseEntity.ok(trabajoRealizadoService.listarTrabajoRealizados());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar trabajo realizado por su identificador",
            description = "Retorna un trabajo realizado en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Trabajo no encontrado", content = @Content)
            }
    )
    public ResponseEntity<TrabajoRealizadoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(trabajoRealizadoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TrabajoRealizadoDto> guardarTrabajo(@RequestBody @Valid TrabajoRealizadoDto trabajoRealizadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajoRealizadoService.guardarTrabajo(trabajoRealizadoDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<TrabajoRealizadoDto> modificarTrabajo(@PathVariable Long id, @RequestBody @Valid TrabajoRealizadoDto trabajoRealizadoDto) {
        return ResponseEntity.ok(this.trabajoRealizadoService.guardarTrabajo(trabajoRealizadoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TrabajoRealizadoDto> eliminarTrabajo(@PathVariable Long id) {
        this.trabajoRealizadoService.eliminarTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}
