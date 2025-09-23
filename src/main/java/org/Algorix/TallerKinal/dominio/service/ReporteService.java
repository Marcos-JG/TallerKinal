package org.Algorix.TallerKinal.dominio.service;

import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;
import org.Algorix.TallerKinal.dominio.repository.ResporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {
    private final ResporteRepository reporteRepository;

    public ReporteService(ResporteRepository resporteRepository) {
        this.reporteRepository = resporteRepository;
    }
    
    public List<ReporteDto> obtenerTodo() {
        return this.reporteRepository.obtenerTodos();
    }

    //2 continua con el servicio
    public ReporteDto obtenerReportePorCodigo(Long codigo){
        return this.reporteRepository.buscarPorId(codigo);
    }

    public ReporteDto guardarReporte(ReporteDto ReporteDto){
        return this.reporteRepository.guardarReporte(ReporteDto);

    }

    public ReporteDto modficarReporte(Long codigo, ModReporteDto modReporteDto){
        return this.reporteRepository.modificarReporte(codigo, modReporteDto);
    }

    public void eliminarReporte(Long codigo){
        this.reporteRepository.eliminarReporte(codigo);
    }
    
}
