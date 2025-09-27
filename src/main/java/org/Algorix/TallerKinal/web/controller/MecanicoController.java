package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/mecanicos")
@Tag(name = "Mecánicos", description = "Operaciones sobre mecánicos")
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
    @Operation(
            summary = "Buscar mecánico por su identificador",
            description = "Retorna un mecánico en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Mecánico no encontrado", content = @Content)
            }
    )
    public ResponseEntity<MecanicoDto> buscarMecanicoPorCodigo(
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.mecanicoService.buscarMecanicoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<MecanicoDto> guardarMecanico(@RequestBody @Valid MecanicoDto mecanicoDto){
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
