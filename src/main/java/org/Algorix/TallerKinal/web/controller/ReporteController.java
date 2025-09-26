package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;
import org.Algorix.TallerKinal.dominio.service.ReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reportes")
@Tag(name = "Reportes", description = "Operaciones (CRUD) sobre los reportes del taller")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    @Operation(summary = "Listar reportes", description = "Retorna todos los reportes",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<List<ReporteDto>> obtenerTodo(){
        return ResponseEntity.ok(this.reporteService.obtenerTodo());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar reporte por su id",
            description = "Retorna un reporte en base MechanicView su id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
                    @ApiResponse(responseCode = "404", description = "reporte no encontrada", content = @Content)
            }
    )
    public ResponseEntity<ReporteDto> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.reporteService.obtenerReportePorCodigo(id));
    }

    @PostMapping
    @Operation(summary = "Crear Reporte",
            responses = {@ApiResponse(responseCode = "201", description = "Reporte creado")})
    public ResponseEntity<ReporteDto> guardarreporte(@RequestBody @Valid ReporteDto reporteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reporteService.guardarReporte(reporteDto));
    }

    @PutMapping("{id}")
    @Operation(summary = "Modificar reporte",
            responses = {@ApiResponse(responseCode = "200", description = "Reporte modificado")})
    public ResponseEntity<ReporteDto> modificarReporte
            (@PathVariable Long id, @RequestBody @Valid ModReporteDto modreporteDto){
        return ResponseEntity.ok(this.reporteService.modficarReporte(id, modreporteDto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Reporte",
            responses = {@ApiResponse(responseCode = "200", description = "Reporte eliminado")})
    public void eliminarreporte(@PathVariable Long id) {
        this.reporteService.eliminarReporte(id);
    }

}

