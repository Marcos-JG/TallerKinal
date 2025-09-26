package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.dominio.exception.AdministradorNoExiste;
import org.Algorix.TallerKinal.dominio.repository.AdministadorRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudAdministrador;
import org.Algorix.TallerKinal.persistence.entity.AdministradorEntity;
import org.Algorix.TallerKinal.web.mapper.AdministradorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministradorEntityRepository implements AdministadorRepository {

    private final CrudAdministrador crudAdministrador;
    private final AdministradorMapper administradorMapper;

    public AdministradorEntityRepository(CrudAdministrador crudAdministrador, AdministradorMapper administradorMapper) {
        this.crudAdministrador = crudAdministrador;
        this.administradorMapper = administradorMapper;
    }

    @Override
    public List<AdministradorDto> obtenerAdministradores() {
        return this.administradorMapper.toDtos(this.crudAdministrador.findAll());
    }

    @Override
    public AdministradorDto buscarPorId(Long id) {
        AdministradorEntity administradorEntity = this.crudAdministrador.findById(id).orElse(null);
        if (administradorEntity == null) {
            throw new AdministradorNoExiste(id);
        } else  {
            return administradorMapper.toDto(administradorEntity);
        }
    }

    @Override
    public AdministradorDto iniciarSesion(UserAdminDto userAdminDto) {
        if (userAdminDto == null || userAdminDto.email() == null || userAdminDto.password() == null) {
            return null;
        }
        AdministradorEntity administradorEntity = this.crudAdministrador.findFirstByCorreo(userAdminDto.email());
        if (administradorEntity == null || administradorEntity.getContrasena() == null) {
            return null;
        }
        if (administradorEntity.getContrasena().equals(userAdminDto.password())) {
            return administradorMapper.toDto(administradorEntity);
        }
        return null;
    }

    @Override
    public AdministradorDto guardarAdministrador(AdministradorDto administradorDto) {
        // Map DTO to entity manually (mapper does not have direct DTO->Entity for AdministradorDto)
        AdministradorEntity administrador = new AdministradorEntity();
        administrador.setNombre(administradorDto.name());
        administrador.setApellido(administradorDto.lastname());
        administrador.setCorreo(administradorDto.email());
        administrador.setContrasena(administradorDto.password());
        administrador.setTelefono(administradorDto.phone());
        this.crudAdministrador.save(administrador);
        return administradorMapper.toDto(administrador);
    }

    @Override
    public AdministradorDto modificarAdministrador(Long id, AdministradorDto administradorDto) {
        AdministradorEntity existing = this.crudAdministrador.findById(id).orElse(null);
        if (existing == null) {
            throw new AdministradorNoExiste(id);
        }
        existing.setNombre(administradorDto.name());
        existing.setApellido(administradorDto.lastname());
        existing.setCorreo(administradorDto.email());
        existing.setContrasena(administradorDto.password());
        existing.setTelefono(administradorDto.phone());
        this.crudAdministrador.save(existing);
        return administradorMapper.toDto(existing);
    }

    @Override
    public void eliminarAdministrador(Long id) {
        AdministradorEntity existing = this.crudAdministrador.findById(id).orElse(null);
        if (existing == null) {
            throw new AdministradorNoExiste(id);
        }
        this.crudAdministrador.deleteById(id);
    }
}
