package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;

import java.util.List;

public interface ClienteRepository {
    List<ClienteDto> obtenerClientes();
    ClienteDto buscarPorId(Long id);
    ClienteDto guardarCliente(ClienteDto clienteDto);
    ClienteDto modificarCliente(Long id, ModClienteDto modClienteDto);
    ClienteDto iniciarSesion(UserClienteDto userClienteDto);
    void eliminarCliente(Long id);
}
