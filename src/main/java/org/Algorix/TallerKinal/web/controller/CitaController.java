package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
    public ResponseEntity<List<CitaDto>> obtenerTodo(){
        return ResponseEntity.ok(this.citaService.obtenerTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<CitaDto> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.citaService.obtenerTodoPorId(id));
    }

    @PostMapping
    public ResponseEntity<CitaDto> guardarCita(@RequestBody  CitaDto citaDto){
        return ResponseEntity.ok(this.citaService.guardarCita(citaDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CitaDto> modificarCita(@PathVariable("id") Long id, @RequestBody  ModCitaDto modCitaDto) {
        return ResponseEntity.ok(this.citaService.modificarCita(id, modCitaDto));
    }

    @DeleteMapping("{id}")
    public void eliminarCita( @PathVariable Long id) {
        this.citaService.eliminarCita(id);
    }
}
