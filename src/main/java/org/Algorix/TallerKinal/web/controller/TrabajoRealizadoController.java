package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.service.TrabajoRealizadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<TrabajoRealizadoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(trabajoRealizadoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TrabajoRealizadoDto> guardarTrabajo(@RequestBody TrabajoRealizadoDto trabajoRealizadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajoRealizadoService.guardarTrabajo(trabajoRealizadoDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<TrabajoRealizadoDto> modificarTrabajo(@PathVariable Long id, @RequestBody TrabajoRealizadoDto trabajoRealizadoDto) {
        return ResponseEntity.ok(this.trabajoRealizadoService.guardarTrabajo(trabajoRealizadoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TrabajoRealizadoDto> eliminarTrabajo(@PathVariable Long id) {
        this.trabajoRealizadoService.eliminarTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}
