package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.dominio.service.MecanicoService;
import org.primefaces.PrimeFaces;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("mecanicoView")
@SessionScope
@Data
public class MecanicoView {
    private final MecanicoService mecanicoService;
    private List<MecanicoDto> mecanicos;
    private MecanicoDto selected;

    // Únicos campos (crear/editar)
    private String editName;
    private String editLastName;
    private String editPhone;

    public MecanicoView(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearEdits();
    }

    public void refresh() {
        try {
            this.mecanicos = new ArrayList<>(mecanicoService.obtenerTodo());
        } catch (Exception e) {
            this.mecanicos = new ArrayList<>();
        }
    }

    private void clearEdits() {
        this.editName = "";
        this.editLastName = "";
        this.editPhone = "";
    }

    public void agregarMecanico() {
        this.selected = null;
        clearEdits();
        PrimeFaces.current().executeScript("PF('ventanaModalMecanico').show()");
    }

    public void prepararEdicionMecanico(MecanicoDto m) {
        this.selected = m;
        if (m != null) {
            this.editName = m.name();
            this.editLastName = m.lastName();
            this.editPhone = m.phone();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalMecanico').show()");
    }

    public void guardarMecanico() {
        try {
            if (this.selected == null) {
                MecanicoDto nuevo = new MecanicoDto(null, editName != null? editName.trim():null, editLastName!=null?editLastName.trim():null, editPhone!=null?editPhone.trim():null);
                mecanicoService.guardarMecanico(nuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mecánico Agregado"));
            } else {
                ModMecanicoDto mod = new ModMecanicoDto(editName, editLastName, editPhone);
                mecanicoService.editarMecanico(selected.idMecanico(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mecánico Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("mecanicosForm:tabla", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalMecanico').hide()");
            this.selected = null;
            clearEdits();
        } catch (DataIntegrityViolationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede guardar", ex.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Operación fallida"));
        }
    }

    public void eliminarMecanico() {
        if (this.selected == null || this.selected.idMecanico() == null) return;
        try {
            mecanicoService.eliminarMecanico(this.selected.idMecanico());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mecánico Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("mecanicosForm:tabla", "growlForm:growlMensajes");
            this.selected = null;
            clearEdits();
        } catch (DataIntegrityViolationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar", ex.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarMecanico() {
        this.selected = null;
        clearEdits();
        PrimeFaces.current().executeScript("PF('ventanaModalMecanico').hide()");
    }
}
