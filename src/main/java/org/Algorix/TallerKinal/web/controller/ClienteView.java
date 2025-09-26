package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.service.ClienteService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("clienteView")
@SessionScope
@Data
public class ClienteView {
    private final ClienteService clienteService;
    private List<ClienteDto> clientes;
    private ClienteDto selected;

    // Campos unificados
    private String editName;
    private String editEmail;
    private String editPassword;
    private String editLastName; // para creación si se requiere

    public ClienteView(ClienteService clienteService) { this.clienteService = clienteService; }

    @PostConstruct
    public void init() {
        refresh();
        clearEdits();
    }

    public void refresh() {
        try { this.clientes = new ArrayList<>(clienteService.obtenerClientes()); }
        catch (Exception e) { this.clientes = new ArrayList<>(); }
    }

    private void clearEdits() { this.editName = ""; this.editEmail = ""; this.editPassword = ""; this.editLastName = ""; }

    public void agregarCliente() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCliente').show()"); }

    public void prepararEdicionCliente(ClienteDto c) {
        this.selected = c; clearEdits();
        if (c != null) { this.editName = c.getName(); this.editEmail = c.getEmail(); this.editPassword = c.getPassword(); this.editLastName = c.getLastName(); }
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').show()");
    }

    public void guardarCliente() {
        try {
            if (this.selected == null) {
                ClienteDto nuevo = new ClienteDto(null, editName, editLastName, editEmail, editPassword);
                clienteService.guardarCliente(nuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Agregado"));
            } else {
                ModClienteDto mod = new ModClienteDto(editName, editEmail, editPassword);
                clienteService.modificarCliente(selected.getId_cliente(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("clientesForm:tablaClientes", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
            this.selected = null; clearEdits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarCliente() {
        if (this.selected == null || this.selected.getId_cliente() == null) return;
        try { clienteService.eliminarCliente(this.selected.getId_cliente()); FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado")); refresh(); PrimeFaces.current().ajax().update("clientesForm:tablaClientes", "growlForm:growlMensajes"); this.selected = null; clearEdits(); }
        catch (Exception e) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar")); }
    }

    public void cancelarCliente() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()"); }
}
