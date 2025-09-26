package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.dominio.service.ProveedorService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("proveedorView")
@SessionScope
@Data
public class ProveedorView implements Serializable {
    private final ProveedorService proveedorService;
    private List<ProveedorDto> proveedores;
    private ProveedorDto selected;

    // Campos de nuevo proveedor
    private String newCompanyName;
    private String newContact;
    private String newPhone;
    private String newEmail;

    // Campos de edición
    private String editCompanyName;
    private String editContact;
    private String editPhone;
    private String editEmail;

    public ProveedorView(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.proveedores = new ArrayList<>(proveedorService.obtenerTodos());
        } catch (Exception e) {
            this.proveedores = new ArrayList<>();
        }
    }

    public void add() {
        ProveedorDto nuevo = new ProveedorDto(null, newCompanyName, newContact, newPhone, newEmail);
        proveedorService.guardarProveedor(nuevo);
        refresh();
        clearNewForm();
    }

    public void startEdit(ProveedorDto p) {
        this.selected = p;
        if (p != null) {
            this.editCompanyName = p.companyName();
            this.editContact = p.contact();
            this.editPhone = p.phone();
            this.editEmail = p.email();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        ProveedorDto mod = new ProveedorDto(selected.id_proveedor(), editCompanyName, editContact, editPhone, editEmail);
        proveedorService.modificarProveedor(selected.id_proveedor(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(ProveedorDto p) {
        if (p == null || p.id_proveedor() == null) return;
        proveedorService.eliminarProveedor(p.id_proveedor());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    public void clearNewForm() {
        this.newCompanyName = "";
        this.newContact = "";
        this.newPhone = "";
        this.newEmail = "";
    }

    public void clearEditForm() {
        this.editCompanyName = "";
        this.editContact = "";
        this.editPhone = "";
        this.editEmail = "";
    }
}
