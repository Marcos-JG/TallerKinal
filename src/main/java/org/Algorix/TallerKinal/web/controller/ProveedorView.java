package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ProveedorDto;
import org.Algorix.TallerKinal.dominio.service.ProveedorService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ViewScoped
public class ProveedorView {
    @Autowired
    ProveedorService proveedorService;
    private List<ProveedorDto> proveedores;
    private ProveedorDto proveedorSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(ProveedorView.class);
    Logger looger = LoggerFactory.getLogger(ProveedorView.class);

    private String editCompanyName;
    private String editContact;
    private String editPhone;
    private String editEmail;

    public ProveedorView(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    public ProveedorView() {
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.proveedores = this.proveedorService.obtenerTodos();
        this.proveedores.forEach(cliente -> logger.info(cliente.toString()));

    }

    public void refresh() {
        try {
            this.proveedores = new ArrayList<>(proveedorService.obtenerTodos());
        } catch (Exception e) {
            this.proveedores = new ArrayList<>();
        }
    }

    public void agregarProveedor() {
        this.proveedorSeleccionado = null;
        this.editCompanyName = "";
        this.editContact = "";
        this.editPhone = "";
        this.editEmail = "";
        PrimeFaces.current().executeScript("PF('ventanaModalProveedor').show()");
    }

    public void prepararEdicion(ProveedorDto p) {
        this.proveedorSeleccionado = p;
        if (p != null) {
            this.editCompanyName = p.companyName();
            this.editContact = p.contact();
            this.editPhone = p.phone();
            this.editEmail = p.email();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalProveedor').show()");
    }

    public void guardarProveedor() {
        try {
            ProveedorDto dto;
            if (this.proveedorSeleccionado == null || this.proveedorSeleccionado.id_proveedor() == null) {
                dto = new ProveedorDto(null, editCompanyName, editContact, editPhone, editEmail);
            } else {
                dto = new ProveedorDto(this.proveedorSeleccionado.id_proveedor(), editCompanyName, editContact, editPhone, editEmail);
            }
            ProveedorDto guardado = this.proveedorService.guardarProveedor(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.proveedorSeleccionado == null || this.proveedorSeleccionado.id_proveedor() == null ? "Proveedor Agregado" : "Proveedor Modificado"));
            refresh();
            this.proveedorSeleccionado = guardado;
            PrimeFaces.current().ajax().update("formProveedores:tablaProveedores", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalProveedor').hide()");
            this.proveedorSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al guardar proveedor", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarProveedor() {
        if (this.proveedorSeleccionado == null || this.proveedorSeleccionado.id_proveedor() == null) return;
        try {
            this.proveedorService.eliminarProveedor(this.proveedorSeleccionado.id_proveedor());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formProveedores:tablaProveedores", "growlMensajes");
            this.proveedorSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar proveedor", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarProveedor() {
        this.proveedorSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalProveedor').hide()");
    }
}
