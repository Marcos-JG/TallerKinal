package org.Algorix.TallerKinal.dominio.repository;



import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;

import java.util.List;

public interface ResporteRepository {
    List<ReporteDto> obtenerTodos();
    ReporteDto buscarPorId(Long id);
    ReporteDto guardarReporte(ReporteDto reporteDto);
    ReporteDto modificarReporte(Long id, ModReporteDto modReporteDto);
    void eliminarReporte(Long id);
}
