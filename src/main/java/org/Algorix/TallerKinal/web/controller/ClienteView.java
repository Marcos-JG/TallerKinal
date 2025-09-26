package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.ModClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.dominio.service.AdministradorService;
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
    private final AdministradorService administradorService;
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

    // Campos de login (unico formulario)
    private String loginEmail;
    private String loginPassword;

    // Usuario actual en sesión
    private ClienteDto currentCliente;
    private boolean loggedIn = false;

    public ClienteView(ClienteService clienteService, AdministradorService administradorService) {
        this.clienteService = clienteService;
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

    // -------- Inicio de sesión unificado --------
    /**
     * Intenta autenticar como administrador primero; si no, intenta como cliente.
     * En caso de autenticación exitosa redirige a la página correspondiente.
     * Retorna outcome JSF para navegación o null si se queda en la misma página.
     */
    public String login() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        // Intentar admin
        try {
            UserAdminDto admin = new UserAdminDto(loginEmail, loginPassword);
            var adminDto = administradorService.iniciarSesion(admin);
            if (adminDto != null) {
                // Auth admin ok
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión", "Administrador autenticado."));
                System.out.println("[LOGIN] Administrador autenticado: " + adminDto.email());
                // limpiar campos y navegar a administradores.xhtml
                this.loginEmail = "";
                this.loginPassword = "";
                return "/administradores.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            // No detenerse; si admin falla por excepción, intentamos cliente
            System.out.println("[LOGIN] Error al autenticar admin (se intentará cliente): " + e.getMessage());
        }

        // Intentar cliente
        try {
            UserClienteDto user = new UserClienteDto(loginEmail, loginPassword);
            var clienteDto = clienteService.iniciarSesion(user);
            if (clienteDto != null) {
                this.currentCliente = clienteDto;
                this.loggedIn = true;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión", "Cliente autenticado."));
                System.out.println("[LOGIN] Cliente autenticado: " + clienteDto.getEmail() + " id=" + clienteDto.getId_cliente());
                this.loginEmail = "";
                this.loginPassword = "";
                return "/cliente.xhtml?faces-redirect=true";
            } else {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Inicio de sesión", "Credenciales incorrectas."));
                System.out.println("[LOGIN] Credenciales incorrectas para: " + loginEmail);
            }
        } catch (Exception e) {
            String msg = e.getMessage() == null ? "Error desconocido" : e.getMessage();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicio de sesión", msg));
            System.out.println("[LOGIN] Error al autenticar cliente: " + msg);
            e.printStackTrace();
        }
        return null; // quedarse en la misma página
    }

    public void logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.currentCliente = null;
        this.loggedIn = false;
        this.loginEmail = "";
        this.loginPassword = "";
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout", "Usuario desconectado."));
        System.out.println("[LOGOUT] Usuario desconectado");
    }
}
