package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.*;
import org.Algorix.TallerKinal.dominio.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicoService {
    private final MecanicoRepository mecanicoRepository;

    @Autowired
    public MecanicoService(MecanicoRepository mecanicoRepository) {
        this.mecanicoRepository = mecanicoRepository;
    }

    public List<MecanicoDto> obtenerTodo() {
        return mecanicoRepository.listarMecanico();
    }

    public MecanicoDto buscarMecanicoPorCodigo(Long codigo){
        return this.mecanicoRepository.buscarMecanicoPorCodigo(codigo);
    }

    public MecanicoDto guardarMecanico(MecanicoDto mecanicoDto){
        return this.mecanicoRepository.guardarMecanico(mecanicoDto);
    }

    public MecanicoDto editarMecanico(Long codigo, ModMecanicoDto modMecanicoDto){
        return this.mecanicoRepository.editarMecanico(codigo, modMecanicoDto);
    }

    public void eliminarMecanico(Long codigo){
        this.mecanicoRepository.eliminarMecanico(codigo);
    }

}