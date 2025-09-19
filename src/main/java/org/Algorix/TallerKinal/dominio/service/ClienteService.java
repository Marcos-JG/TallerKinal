package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDto> obtenerClientes() {
        return this.clienteRepository.obtenerClientes();
    }

    public  ClienteDto buscarPorId(Long id){
        return this.clienteRepository.buscarPorId(id);
    }

    public ClienteDto guardarCliente(ClienteDto clienteDto){
        return this.clienteRepository.guardarCliente(clienteDto);

    }

    public ClienteDto iniciarSesion(UserClienteDto userClienteDto){
        return this.clienteRepository.iniciarSesion(userClienteDto);
    }

    public ClienteDto modificarCliente(Long id, ModClienteDto modClienteDto) {
        return this.clienteRepository.modificarCliente(id, modClienteDto);
    }

    public void eliminarCliente(Long id){
         this.clienteRepository.eliminarCliente(id);
    }

}
