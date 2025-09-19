package org.Algorix.TallerKinal.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vehiculos")
@Tag(name = "Vehiculo")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDto>> obtenerTodo(){
        return ResponseEntity.ok(this.vehiculoService.obtenerTodo());
    }

    @GetMapping("{placas}")
    @Operation(
            summary = "Buscar Vehiculo por su placa",
            description = "Retorna una vehiculo en base a su placa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
                    @ApiResponse(responseCode = "404", description = "Vehiculo no encontrada", content = @Content)
            }
    )
    public ResponseEntity<VehiculoDto> buscarPorPlaca(@PathVariable("placas") String placas){
        return ResponseEntity.ok(this.vehiculoService.buscarPorPlaca(placas));
    }

    @PostMapping
    public ResponseEntity<VehiculoDto> guardarVehiculo(@RequestBody VehiculoDto vehiculoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.vehiculoService.guardarVehiculo(vehiculoDto));
    }

    @PutMapping("{placas}")
    public ResponseEntity<VehiculoDto> modificarVehiculo
            (@PathVariable String placas, @RequestBody @Valid ModVehiculoDto modVehiculoDto){
        return ResponseEntity.ok(this.vehiculoService.modificarVehiculo(placas, modVehiculoDto));
    }

}
