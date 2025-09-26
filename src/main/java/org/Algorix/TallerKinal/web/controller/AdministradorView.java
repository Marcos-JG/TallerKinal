package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.service.AdministradorService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("administradorView")
@SessionScope
@Data
public class AdministradorView {
    private final AdministradorService administradorService;
    private List<AdministradorDto> administradores;
    private AdministradorDto selected;

    // Campos unificados para crear/editar
    private String editName;
    private String editLastName;
    private String editEmail;
    private String editPassword;
    private String editPhone;

    public AdministradorView(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearEdits();
    }

    public void refresh() {
        try {
            List<AdministradorDto> list = administradorService.obtenerAdministradores();
            this.administradores = list == null ? new ArrayList<>() : new ArrayList<>(list);
        } catch (Exception e) {
            this.administradores = new ArrayList<>();
        }
    }

    private void clearEdits() {
        this.editName = "";
        this.editLastName = "";
        this.editEmail = "";
        this.editPassword = "";
        this.editPhone = "";
    }

    public void agregarAdministrador() {
        this.selected = null;
        clearEdits();
        PrimeFaces.current().executeScript("PF('ventanaModalAdministrador').show()");
    }

    public void prepararEdicionAdministrador(AdministradorDto a) {
        this.selected = a;
        clearEdits();
        if (a != null) {
            this.editName = a.name();
            this.editLastName = a.lastname();
            this.editEmail = a.email();
            this.editPassword = a.password();
            this.editPhone = a.phone();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalAdministrador').show()");
    }

    public void guardarAdministrador() {
        try {
            if (this.selected == null) {
                AdministradorDto dto = new AdministradorDto(null, editName, editLastName, editEmail, editPassword, editPhone);
                administradorService.guardarAdministrador(dto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrador Agregado"));
            } else {
                AdministradorDto mod = new AdministradorDto(selected.id_admin(), editName, editLastName, editEmail, editPassword, editPhone);
                administradorService.modificarAdministrador(selected.id_admin(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrador Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("administradoresForm:tabla", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalAdministrador').hide()");
            this.selected = null;
            clearEdits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarAdministrador() {
        if (this.selected == null || this.selected.id_admin() == null) return;
        try {
            administradorService.eliminarAdministrador(this.selected.id_admin());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrador Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("administradoresForm:tabla", "growlForm:growlMensajes");
            this.selected = null;
            clearEdits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarAdministrador() {
        this.selected = null;
        clearEdits();
        PrimeFaces.current().executeScript("PF('ventanaModalAdministrador').hide()");
    }
}
