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
        }else  {
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



}
