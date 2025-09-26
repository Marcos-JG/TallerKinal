package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ReporteDto;
import org.Algorix.TallerKinal.dominio.dto.ModReporteDto;
import org.Algorix.TallerKinal.dominio.service.ReporteService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("reporteView")
@SessionScope
@Data
public class ReporteView implements Serializable {
    private final ReporteService reporteService;
    private List<ReporteDto> reportes;
    private ReporteDto selected;

    // Campos unificados crear/editar
    private Long editIdCita;
    private String editDescription;
    private BigDecimal editTotal; // en ReporteDto es BigDecimal, ModReporteDto usa Double

    public ReporteView(ReporteService reporteService) { this.reporteService = reporteService; }

    @PostConstruct
    public void init(){ refresh(); clearEdits(); }

    public void refresh(){
        try { this.reportes = new ArrayList<>(reporteService.obtenerTodo()); }
        catch (Exception e){ this.reportes = new ArrayList<>(); }
    }

    private void clearEdits(){ this.editIdCita=null; this.editDescription=""; this.editTotal=null; }

    public void agregarReporte(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalReporte').show()"); }

    public void prepararEdicionReporte(ReporteDto r){ this.selected=r; clearEdits(); if(r!=null){ this.editIdCita = r.idCita(); this.editDescription = r.description(); this.editTotal = r.total(); } PrimeFaces.current().executeScript("PF('ventanaModalReporte').show()"); }

    public void guardarReporte(){
        try {
            if(this.selected==null){
                ReporteDto dto = new ReporteDto(null, editIdCita, editDescription, editTotal==null? BigDecimal.ZERO: editTotal);
                reporteService.guardarReporte(dto);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Reporte Agregado"));
            } else {
                // ModReporteDto usa Double total
                Double totalDouble = editTotal==null? 0d : editTotal.doubleValue();
                ModReporteDto mod = new ModReporteDto(editIdCita!=null?editIdCita:selected.idCita(), editDescription, totalDouble);
                reporteService.modficarReporte(selected.id_reporte(), mod);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Reporte Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("reportesForm:tablaReportes","growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalReporte').hide()");
            this.selected=null; clearEdits();
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo guardar"));
        }
    }

    public void eliminarReporte(){
        if(this.selected==null || this.selected.id_reporte()==null) return;
        try {
            reporteService.eliminarReporte(this.selected.id_reporte());
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Reporte Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("reportesForm:tablaReportes","growlForm:growlMensajes");
            this.selected=null; clearEdits();
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo eliminar"));
        }
    }

    public void cancelarReporte(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalReporte').hide()"); }
}

