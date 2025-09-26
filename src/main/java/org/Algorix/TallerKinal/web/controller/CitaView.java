package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.service.CitaService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("citaView")
@SessionScope
@Data
public class CitaView implements Serializable {
    private final CitaService citaService;
    private List<CitaDto> citas;
    private CitaDto selected;

    // Campos unificados (crear / editar)
    private LocalDate editAppointmentDate;
    private Long editIdEmpleado;
    private Long editIdCliente;
    private String editAppointmentType;
    private Long editIdVehiculo;
    private String editStatus;
    private String editComments;

    public CitaView(CitaService citaService) { this.citaService = citaService; }

    @PostConstruct public void init(){ refresh(); clearEdits(); }

    public void refresh(){ try { this.citas = new ArrayList<>(citaService.obtenerTodo()); } catch (Exception e){ this.citas = new ArrayList<>(); } }

    private void clearEdits(){ this.editAppointmentDate=null; this.editIdEmpleado=null; this.editIdCliente=null; this.editAppointmentType=null; this.editIdVehiculo=null; this.editStatus=null; this.editComments=null; }

    public void agregarCita(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCita').show()"); }

    public void prepararEdicionCita(CitaDto c){ this.selected=c; clearEdits(); if(c!=null){ this.editAppointmentDate=c.appointmentDate(); this.editIdEmpleado=c.idEmpleado(); this.editIdCliente=c.idCliente(); this.editAppointmentType=c.appointmentType(); this.editIdVehiculo=c.idVehiculo(); this.editStatus=c.status(); this.editComments=c.comments(); } PrimeFaces.current().executeScript("PF('ventanaModalCita').show()"); }

    public void guardarCita(){ try { if(this.selected==null){ CitaDto nuevo = new CitaDto(null, editAppointmentDate, editIdEmpleado, editIdCliente, editAppointmentType, editIdVehiculo, editStatus, editComments); citaService.guardarCita(nuevo); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cita Agregada")); } else { ModCitaDto mod = new ModCitaDto(editIdEmpleado!=null?editIdEmpleado:selected.idEmpleado(), editAppointmentDate, editAppointmentType!=null?editAppointmentType:selected.appointmentType(), editIdVehiculo!=null?editIdVehiculo:selected.idVehiculo(), editStatus, editComments); citaService.modificarCita(selected.id_cita(), mod); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cita Modificada")); } refresh(); PrimeFaces.current().ajax().update("citasForm:tabla", "growlForm:growlMensajes"); PrimeFaces.current().executeScript("PF('ventanaModalCita').hide()"); this.selected=null; clearEdits(); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo guardar")); } }

    public void eliminarCita(){ if(this.selected==null || this.selected.id_cita()==null) return; try { citaService.eliminarCita(this.selected.id_cita()); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cita Eliminada")); refresh(); PrimeFaces.current().ajax().update("citasForm:tabla", "growlForm:growlMensajes"); this.selected=null; clearEdits(); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo eliminar")); } }

    public void cancelarCita(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCita').hide()"); }
}
