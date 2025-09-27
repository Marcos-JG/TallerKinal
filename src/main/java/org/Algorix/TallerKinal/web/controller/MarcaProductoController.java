package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/marcas-productos")
@Tag(name = "Marcas", description = "Operaciones sobre marcas de productos")
public class MarcaProductoController {
    private final MarcaProductoService marcaProductoService;

    @Autowired
    public MarcaProductoController(MarcaProductoService marcaProductoService) {
        this.marcaProductoService = marcaProductoService;
    }

    @GetMapping
    public List<MarcaProductoDto> listarMarcas() {
        return marcaProductoService.obtenerTodo();
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Buscar marca por su identificador",
            description = "Retorna una marca en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Marca no encontrada", content = @Content)
            }
    )
    public ResponseEntity<MarcaProductoDto> obtenerMarcaPorCodigo(
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.marcaProductoService.obtenerMarcaPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<MarcaProductoDto> guardarMarca(@RequestBody @Valid MarcaProductoDto marcaProductoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.marcaProductoService.guardarMarca(marcaProductoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> modificarMarca
            (@PathVariable Long codigo, @RequestBody @Valid ModMarcaProductoDto modMarcaProductoDto){
        return ResponseEntity.ok(this.marcaProductoService.modificarMarca(codigo,modMarcaProductoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<MarcaProductoDto> eliminarMarca(@PathVariable Long codigo){
        this.marcaProductoService.eliminarMarca(codigo);
        return ResponseEntity.ok().build();
    }

}
