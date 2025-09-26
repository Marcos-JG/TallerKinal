package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.AdministradorDto;
import org.Algorix.TallerKinal.dominio.service.AdministradorService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("administradorView")
@SessionScope
@Data
public class AdministradorView implements Serializable {
    private final AdministradorService administradorService;
    private List<AdministradorDto> administradores;
    private AdministradorDto selected;

    private String newName;
    private String newLastName;
    private String newEmail;
    private String newPassword;
    private String newPhone;

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
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            List<AdministradorDto> list = administradorService.obtenerAdministradores();
            this.administradores = list == null ? new ArrayList<>() : new ArrayList<>(list);
        } catch (Exception e) {
            this.administradores = new ArrayList<>();
        }
    }

    public void add() {
        AdministradorDto dto = new AdministradorDto(null, newName, newLastName, newEmail, newPassword, newPhone);
        administradorService.guardarAdministrador(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(AdministradorDto a) {
        this.selected = a;
        if (a != null) {
            this.editName = a.name();
            this.editLastName = a.lastname();
            this.editEmail = a.email();
            this.editPassword = a.password();
            this.editPhone = a.phone();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        AdministradorDto mod = new AdministradorDto(selected.id_admin(), editName, editLastName, editEmail, editPassword, editPhone);
        administradorService.modificarAdministrador(selected.id_admin(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(AdministradorDto a) {
        if (a == null || a.id_admin() == null) return;
        administradorService.eliminarAdministrador(a.id_admin());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    public void clearNewForm() {
        this.newName = ""; this.newLastName = ""; this.newEmail = ""; this.newPassword = ""; this.newPhone = "";
    }
    public void clearEditForm() {
        this.editName = ""; this.editLastName = ""; this.editEmail = ""; this.editPassword = ""; this.editPhone = "";
    }
}
