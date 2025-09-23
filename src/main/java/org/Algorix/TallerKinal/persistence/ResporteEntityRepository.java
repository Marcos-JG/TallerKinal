package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;
import org.Algorix.TallerKinal.dominio.exception.ReporteNoExiste;
import org.Algorix.TallerKinal.dominio.repository.ResporteRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudReporte;
import org.Algorix.TallerKinal.persistence.entity.ReporteEntity;
import org.Algorix.TallerKinal.web.mapper.ReporteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResporteEntityRepository implements ResporteRepository {
    private final CrudReporte crudReporte;
    private final ReporteMapper reporteMapper;

    public ResporteEntityRepository(CrudReporte crudReporte, ReporteMapper reporteMapper) {
        this.crudReporte = crudReporte;
        this.reporteMapper = reporteMapper;
    }

    @Override
    public List<ReporteDto> obtenerTodos() {
        return this.reporteMapper.toDto(this.crudReporte.findAll());
    }

    @Override
    public ReporteDto buscarPorId(Long id) {
        ReporteEntity reporteEntity = this.crudReporte.findById(id).orElse(null);
        if (reporteEntity == null) throw new ReporteNoExiste(id);{

        }
        return this.reporteMapper.toDto(this.crudReporte.findById(id).orElse(null));
    }

    @Override
    public ReporteDto guardarReporte(ReporteDto reporteDto) {
        ReporteEntity reporteEntity = this.reporteMapper.toEntity(reporteDto);
        this.crudReporte.save(reporteEntity);
        return this.reporteMapper.toDto(reporteEntity);
    }

    @Override
    public ReporteDto modificarReporte(Long id, ModReporteDto modReporteDto) {
        ReporteEntity reporteEntity = this.crudReporte.findById(id).orElse(null);
        if (reporteEntity == null) {
            throw new ReporteNoExiste(id);
        }
        this.reporteMapper.modificarEntityFromDto(modReporteDto, reporteEntity);
        return this.reporteMapper.toDto(this.crudReporte.save(reporteEntity));
    }

    @Override
    public void eliminarReporte(Long id) {
    ReporteEntity reporteEntity = this.crudReporte.findById(id).orElse(null);
    if (reporteEntity == null) {
        throw new ReporteNoExiste(id);
    } else {
        this.crudReporte.deleteById(id);
        }
    }
}
