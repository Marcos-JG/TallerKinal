package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/citas")
@Tag(name = "Cita")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    @Operation(summary = "Listar citas", description = "Retorna todas las citas",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")} )
    public ResponseEntity<List<CitaDto>> obtenerTodo(){
        return ResponseEntity.ok(this.citaService.obtenerTodo());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar cita por su identificador",
            description = "Retorna una cita en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
            }
    )
    public ResponseEntity<CitaDto> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.citaService.obtenerTodoPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear cita",
            responses = {@ApiResponse(responseCode = "200", description = "Cita creada")} )
    public ResponseEntity<CitaDto> guardarCita(@RequestBody @Valid CitaDto citaDto){
        return ResponseEntity.ok(this.citaService.guardarCita(citaDto));
    }

    @PutMapping("{id}")
    @Operation(summary = "Modificar cita",
            responses = {@ApiResponse(responseCode = "200", description = "Cita modificada")} )
    public ResponseEntity<CitaDto> modificarCita(@PathVariable("id") Long id, @RequestBody @Valid ModCitaDto modCitaDto) {
        return ResponseEntity.ok(this.citaService.modificarCita(id, modCitaDto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar cita",
            responses = {@ApiResponse(responseCode = "200", description = "Cita eliminada")} )
    public void eliminarCita( @PathVariable Long id) {
        this.citaService.eliminarCita(id);
    }
}
