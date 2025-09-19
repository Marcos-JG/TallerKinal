package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.dominio.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorDto> obtenerTodos() {
        return proveedorRepository.obtenerTodos();
    }

    public ProveedorDto buscarPorId(Long id) {
        return proveedorRepository.buscarPorId(id);
    }

    public ProveedorDto guardarProveedor(ProveedorDto proveedorDto) {
        return proveedorRepository.guardarProveedor(proveedorDto);
    }

    public ProveedorDto modificarProveedor(Long id, ProveedorDto proveedorDto) {
        return proveedorRepository.modificarProveedor(id, proveedorDto);
    }

    public void eliminarProveedor(Long id) {
        proveedorRepository.eliminarProveedor(id);
    }
}
