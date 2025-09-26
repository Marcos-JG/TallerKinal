package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.dominio.service.MecanicoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("mecanicoView")
@SessionScope
@Data
public class MecanicoView implements Serializable {
    private final MecanicoService mecanicoService;
    private List<MecanicoDto> mecanicos;
    private MecanicoDto selected;

    // Campos de nuevo mecánico
    private String newName;
    private String newLastName;
    private String newPhone;

    // Campos de edición
    private String editName;
    private String editLastName;
    private String editPhone;

    public MecanicoView(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.mecanicos = new ArrayList<>(mecanicoService.obtenerTodo());
        } catch (Exception e) {
            this.mecanicos = new ArrayList<>();
        }
    }

    // CRUD
    public void add() {
        // Normalizar/recortar entrada antes de crear DTO para evitar variaciones que provoquen duplicados
        String n = newName == null ? "" : newName.trim();
        String ln = newLastName == null ? "" : newLastName.trim();
        String ph = newPhone == null ? "" : newPhone.trim();
        MecanicoDto nuevo = new MecanicoDto(null, n, ln, ph);
        try {
            mecanicoService.guardarMecanico(nuevo);
            refresh();
            clearNewForm();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Mecánico creado."));
            PrimeFaces.current().ajax().update(":mecanicosForm:tabla", ":msgsForm:msgs");
        } catch (DataIntegrityViolationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede crear", ex.getMessage()));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al crear el mecánico."));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        }
    }

    public void startEdit(MecanicoDto m) {
        this.selected = m;
        if (m != null) {
            this.editName = m.name();
            this.editLastName = m.lastName();
            this.editPhone = m.phone();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        ModMecanicoDto mod = new ModMecanicoDto(editName, editLastName, editPhone);
        try {
            mecanicoService.editarMecanico(selected.idMecanico(), mod);
            refresh();
            clearEditForm();
            this.selected = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Mecánico modificado."));
            PrimeFaces.current().ajax().update(":mecanicosForm:tabla", ":msgsForm:msgs", ":editContainer");
        } catch (DataIntegrityViolationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede editar", ex.getMessage()));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al modificar el mecánico."));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        }
    }

    public void delete(MecanicoDto m) {
        if (m == null || m.idMecanico() == null) return;
        try {
            mecanicoService.eliminarMecanico(m.idMecanico());
            refresh();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Mecánico eliminado."));
            PrimeFaces.current().ajax().update(":mecanicosForm:tabla", ":msgsForm:msgs");
        } catch (DataIntegrityViolationException ex) {
            // Mostrar mensaje amigable en la UI cuando hay citas relacionadas
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar", ex.getMessage()));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al eliminar."));
            PrimeFaces.current().ajax().update(":msgsForm:msgs");
        }
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    // Utilidades de formulario
    public void clearNewForm() {
        this.newName = "";
        this.newLastName = "";
        this.newPhone = "";
    }

    public void clearEditForm() {
        this.editName = "";
        this.editLastName = "";
        this.editPhone = "";
    }
}
