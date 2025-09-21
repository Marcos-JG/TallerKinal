package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.dominio.service.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/administradores")
@Tag(name = "Administradores", description = "Operaciones sobre administradores")
public class AdministradorController {
    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    @Operation(summary = "Listar administradores", description = "Retorna todos los administradores",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<List<AdministradorDto>> obtenerAdministradores() {
        return ResponseEntity.ok(administradorService.obtenerAdministradores());
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión de administrador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content)
            })
    public ResponseEntity<AdministradorDto> iniciarSesion(@RequestBody @Valid UserAdminDto userAdminDto) {
        AdministradorDto administrador = administradorService.iniciarSesion(userAdminDto);
        if (administrador != null) {
            System.out.println("Inicio de sesión exitoso para: " + userAdminDto.email());
            return ResponseEntity.ok(administrador);
        } else {
            System.out.println("Inicio de sesión fallido para: " + userAdminDto.email());
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}
