package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;

import java.util.List;

public interface ProveedorRepository {
    List<ProveedorDto> obtenerTodos();
    ProveedorDto buscarPorId(Long id);
    ProveedorDto guardarProveedor(ProveedorDto proveedorDto);
    ProveedorDto modificarProveedor(Long id, ProveedorDto proveedorDto);
    void eliminarProveedor(Long id);
}
