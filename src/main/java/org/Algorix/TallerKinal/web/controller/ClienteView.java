package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.service.ClienteService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("clienteView")
@SessionScope
@Data
public class ClienteView implements Serializable {
    private final ClienteService clienteService;
    private List<ClienteDto> clientes;
    private ClienteDto selected;

    // Campos de nuevo cliente
    private String newName;
    private String newLastName;
    private String newEmail;
    private String newPassword;

    // Campos de edición (lastName no se edita, acorde MechanicView ModClienteDto)
    private String editName;
    private String editEmail;
    private String editPassword;

    public ClienteView(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.clientes = new ArrayList<>(clienteService.obtenerClientes());
        } catch (Exception e) {
            this.clientes = new ArrayList<>();
        }
    }

    // CRUD
    public void add() {
        ClienteDto nuevo = new ClienteDto(null, newName, newLastName, newEmail, newPassword);
        clienteService.guardarCliente(nuevo);
        refresh();
        clearNewForm();
    }

    public void startEdit(ClienteDto c) {
        this.selected = c;
        if (c != null) {
            this.editName = c.getName();
            this.editEmail = c.getEmail();
            this.editPassword = c.getPassword();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        ModClienteDto mod = new ModClienteDto(editName, editEmail, editPassword);
        clienteService.modificarCliente(selected.getId_cliente(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(ClienteDto c) {
        if (c == null || c.getId_cliente() == null) return;
        clienteService.eliminarCliente(c.getId_cliente());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    // Utilidades de formulario
    public void clearNewForm() {
        this.newName = "";
        this.newLastName = "";
        this.newEmail = "";
        this.newPassword = "";
    }

    public void clearEditForm() {
        this.editName = "";
        this.editEmail = "";
        this.editPassword = "";
    }
}
