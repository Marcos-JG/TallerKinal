package org.Algorix.TallerKinal.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Clientes", description = "Operaciones sobre clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar cliente por su identificador",
            description = "Retorna un cliente en base MechanicView su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
            }
    )
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.clienteService.buscarPorId(id));
    }

    @PostMapping
    public  ResponseEntity<ClienteDto> insertarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.guardarCliente(clienteDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteDto> modificarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto modClienteDto) {
        return ResponseEntity.ok(this.clienteService.modificarCliente(id, modClienteDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClienteDto> eliminarCliente(@PathVariable Long id) {
        this.clienteService.eliminarCliente(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteDto> iniciarSesion(@RequestBody @Valid UserClienteDto userClienteDto) {
        ClienteDto cliente = clienteService.iniciarSesion(userClienteDto);
        if (cliente != null) {
            System.out.println("Inicio de sesión exitoso para: " + userClienteDto.email());
            return ResponseEntity.ok(cliente);
        } else {
            System.out.println("Inicio de sesión fallido para: " + userClienteDto.email());
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
}
