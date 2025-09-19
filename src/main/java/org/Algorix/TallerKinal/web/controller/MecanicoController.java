package org.Algorix.TallerKinal.web.controller;

import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.dominio.service.MecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/mecanico")
public class MecanicoController {
    private final MecanicoService mecanicoService;

    @Autowired
    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @GetMapping
    public List<MecanicoDto> listarMecanico() {
        return mecanicoService.obtenerTodo();
    }

    @GetMapping("{codigo}")
    public ResponseEntity<MecanicoDto> buscarMecanicoPorCodigo(
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.mecanicoService.buscarMecanicoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<MecanicoDto> guardarMecanico(@RequestBody MecanicoDto mecanicoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mecanicoService.guardarMecanico(mecanicoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<MecanicoDto> modificarMecanico
            (@PathVariable Long codigo, @RequestBody @Valid ModMecanicoDto modMecanicoDto){
        return ResponseEntity.ok(this.mecanicoService.editarMecanico(codigo,modMecanicoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> eliminarMarca(@PathVariable Long codigo){
        this.mecanicoService.eliminarMecanico(codigo);
        return ResponseEntity.ok().build();
    }

}
