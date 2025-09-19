package org.Algorix.TallerKinal.dominio.repository;
import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;

import java.util.List;

public interface MecanicoRepository {
    List<MecanicoDto> listarMecanico();
    MecanicoDto buscarMecanicoPorCodigo(Long codigo);
    void eliminarMecanico(Long codigo);
    MecanicoDto editarMecanico(Long codigo, ModMecanicoDto modMecanicoDto);
    MecanicoDto guardarMecanico(MecanicoDto mecanicoDto);
}
