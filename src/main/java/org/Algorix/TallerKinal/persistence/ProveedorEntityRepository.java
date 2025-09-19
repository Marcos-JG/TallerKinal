package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.dominio.repository.ProveedorRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudProveedor;
import org.Algorix.TallerKinal.persistence.entity.ProveedorEntity;
import org.Algorix.TallerKinal.web.mapper.ProveedorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorEntityRepository implements ProveedorRepository {
    private final CrudProveedor crudProveedor;
    private final ProveedorMapper proveedorMapper;

    public ProveedorEntityRepository(CrudProveedor crudProveedor, ProveedorMapper proveedorMapper) {
        this.crudProveedor = crudProveedor;
        this.proveedorMapper = proveedorMapper;
    }

    @Override
    public List<ProveedorDto> obtenerTodos() {
        return proveedorMapper.toDto((List<ProveedorEntity>) crudProveedor.findAll());
    }

    @Override
    public ProveedorDto buscarPorId(Long id) {
        return crudProveedor.findById(id)
                .map(proveedorMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProveedorDto guardarProveedor(ProveedorDto proveedorDto) {
        ProveedorEntity entity = proveedorMapper.toEntity(proveedorDto);
        ProveedorEntity saved = crudProveedor.save(entity);
        return proveedorMapper.toDto(saved);
    }

    @Override
    public ProveedorDto modificarProveedor(Long id, ProveedorDto proveedorDto) {
        ProveedorEntity entity = crudProveedor.findById(id).orElse(null);
        if (entity == null) return null;
        entity.setNombreEmpresa(proveedorDto.commpanyName());
        entity.setContacto(proveedorDto.contact());
        entity.setTelefono(proveedorDto.phone());
        entity.setCorreo(proveedorDto.email());
        return proveedorMapper.toDto(crudProveedor.save(entity));
    }

    @Override
    public void eliminarProveedor(Long id) {
        crudProveedor.deleteById(id);
    }
}
