package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.service.TrabajoRealizadoService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("trabajoRealizadoView")
@SessionScope
@Data
public class TrabajoRealizadoView {
    private final TrabajoRealizadoService trabajoRealizadoService;
    private List<TrabajoRealizadoDto> trabajos;
    private TrabajoRealizadoDto selected;

    // Campos unificados
    private Long editIdCita; // sólo al crear
    private String editDescription;
    private BigDecimal editLaborCost;
    private BigDecimal editTotalWork;

    public TrabajoRealizadoView(TrabajoRealizadoService trabajoRealizadoService) { this.trabajoRealizadoService = trabajoRealizadoService; }

    @PostConstruct public void init(){ refresh(); clearEdits(); }

    public void refresh(){ try { this.trabajos = new ArrayList<>(trabajoRealizadoService.listarTrabajoRealizados()); } catch (Exception e){ this.trabajos = new ArrayList<>(); } }

    private void clearEdits(){ this.editIdCita = null; this.editDescription=""; this.editLaborCost=null; this.editTotalWork=null; }

    public void agregarTrabajo(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalTrabajo').show()"); }

    public void prepararEdicionTrabajo(TrabajoRealizadoDto t){ this.selected=t; clearEdits(); if(t!=null){ this.editDescription=t.description(); this.editLaborCost=t.laborCost(); this.editTotalWork=t.totalWork(); this.editIdCita=t.idCita(); } PrimeFaces.current().executeScript("PF('ventanaModalTrabajo').show()"); }

    public void guardarTrabajo(){ try { if(this.selected==null){ TrabajoRealizadoDto dto = new TrabajoRealizadoDto(null, editIdCita, editDescription, editLaborCost, editTotalWork); trabajoRealizadoService.guardarTrabajo(dto); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Trabajo Agregado")); } else { ModTrabajoRealozadoDto mod = new ModTrabajoRealozadoDto(editIdCita, editDescription, editLaborCost, editTotalWork); trabajoRealizadoService.modificarTrabajo(selected.id_trabajo(), mod); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Trabajo Modificado")); } refresh(); PrimeFaces.current().ajax().update("trabajosForm:tablaTrabajos", "growlForm:growlMensajes"); PrimeFaces.current().executeScript("PF('ventanaModalTrabajo').hide()"); this.selected=null; clearEdits(); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo guardar")); } }

    public void eliminarTrabajo(TrabajoRealizadoDto t){ if(t==null|| t.id_trabajo()==null) return; try { trabajoRealizadoService.eliminarTrabajo(t.id_trabajo()); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Trabajo Eliminado")); refresh(); PrimeFaces.current().ajax().update("trabajosForm:tablaTrabajos", "growlForm:growlMensajes"); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo eliminar")); } }

    public void cancelarTrabajo(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalTrabajo').hide()"); }
}
