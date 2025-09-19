package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.exception.ClienteNoExiste;
import org.Algorix.TallerKinal.dominio.exception.CorreoDuplicado;
import org.Algorix.TallerKinal.dominio.repository.ClienteRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudCliente;
import org.Algorix.TallerKinal.persistence.entity.ClienteEntity;
import org.Algorix.TallerKinal.web.mapper.ClienteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteEntityRepository implements ClienteRepository {

    private final CrudCliente crudCliente;
    private final ClienteMapper clienteMapper;

    public ClienteEntityRepository(CrudCliente crudCliente, ClienteMapper clienteMapper) {
        this.crudCliente = crudCliente;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public List<ClienteDto> obtenerClientes() {
        return this.clienteMapper.toDtos(this.crudCliente.findAll());
    }

    @Override
    public ClienteDto buscarPorId(Long id) {
        ClienteEntity clienteEntity = this.crudCliente.findById(id).orElse(null);
        if (clienteEntity == null) {
            throw new ClienteNoExiste(id);
        }else{
            return clienteMapper.toDto(clienteEntity);
        }
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        if (this.crudCliente.findFirstByCorreo(clienteDto.email()) != null) {
            throw new CorreoDuplicado(clienteDto.email());
        }
        ClienteEntity cliente = this.clienteMapper.toEntity(clienteDto);
        // fallback manual mapping si el mapper no mapea correctamente
        if (cliente == null) {
            cliente = new ClienteEntity();
        }
        if (cliente.getNombre() == null) cliente.setNombre(clienteDto.name());
        if (cliente.getApellido() == null) cliente.setApellido(clienteDto.lastName());
        if (cliente.getCorreo() == null) cliente.setCorreo(clienteDto.email());
        if (cliente.getContrasena() == null) cliente.setContrasena(clienteDto.password());

        ClienteEntity saved = this.crudCliente.save(cliente);
        return this.clienteMapper.toDto(saved);
    }

    @Override
    public ClienteDto modificarCliente(Long id, ModClienteDto modClienteDto) {
        ClienteEntity clienteEntity = this.crudCliente.findById(id).orElse(null);
        this.clienteMapper.modificarEntityFromDto(modClienteDto, clienteEntity);
        return this.clienteMapper.toDto(this.crudCliente.save(clienteEntity));
    }

    @Override
    public ClienteDto iniciarSesion(UserClienteDto userClienteDto) {
        if (userClienteDto == null || userClienteDto.email() == null || userClienteDto.password() == null) {
            return null;
        }
        ClienteEntity clienteEntity = this.crudCliente.findFirstByCorreo(userClienteDto.email());
        if (clienteEntity == null || clienteEntity.getContrasena() == null) {
            return null;
        }
        if (clienteEntity.getContrasena().equals(userClienteDto.password())) {
            return clienteMapper.toDto(clienteEntity);
        }
        return null;
    }

    @Override
    public void eliminarCliente(Long id) {
        ClienteEntity clienteEntity = this.crudCliente.findById(id).orElse(null);
        if (clienteEntity == null) {
            throw new ClienteNoExiste(id);
        } else {
            this.crudCliente.deleteById(id);
        }
    }
}
