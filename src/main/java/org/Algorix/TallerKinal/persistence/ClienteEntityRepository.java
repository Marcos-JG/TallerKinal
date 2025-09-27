package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.exception.ClienteNoExiste;
import org.Algorix.TallerKinal.dominio.exception.ContrasenaInvalida;
import org.Algorix.TallerKinal.dominio.exception.CorreoDuplicado;
import org.Algorix.TallerKinal.dominio.exception.CorreoInvalido;
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
        if (this.crudCliente.findFirstByCorreo(clienteDto.getEmail()) != null) {
            throw new CorreoDuplicado(clienteDto.getEmail());
        }
        ClienteEntity cliente = this.clienteMapper.toEntity(clienteDto);
        ClienteEntity saved = this.crudCliente.save(cliente);
        return this.clienteMapper.toDto(saved);
    }

    @Override
    public ClienteDto modificarCliente(Long id, ClienteDto modClienteDto) {
        ClienteEntity clienteEntity = this.crudCliente.findById(id).orElse(null);
        if (clienteEntity == null) {
            throw new ClienteNoExiste(id);
        }
        this.clienteMapper.modificarEntityFromDto(modClienteDto, clienteEntity);
        return this.clienteMapper.toDto(this.crudCliente.save(clienteEntity));
    }

    private boolean emailBasicoValido(String email) {
        if (email == null) return false;
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && dot > at + 1 && dot < email.length() - 1;
    }

    @Override
    public ClienteDto iniciarSesion(UserClienteDto userClienteDto) {
        if (userClienteDto == null || !emailBasicoValido(userClienteDto.email())) {
            throw new CorreoInvalido(userClienteDto == null ? null : userClienteDto.email());
        }
        if (userClienteDto.password() == null || userClienteDto.password().isBlank()) {
            throw new ContrasenaInvalida(userClienteDto.password());
        }
        ClienteEntity clienteEntity = this.crudCliente.findFirstByCorreo(userClienteDto.email());
        if (clienteEntity == null) {
            throw new ClienteNoExiste(null);
        }
        if (clienteEntity.getContrasena() == null || !clienteEntity.getContrasena().equals(userClienteDto.password())) {
            throw new ContrasenaInvalida(userClienteDto.password());
        }
        return clienteMapper.toDto(clienteEntity);
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
