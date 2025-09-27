package org.Algorix.TallerKinal.web.controller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserClienteDto;
import org.Algorix.TallerKinal.dominio.dto.UserAdminDto;
import org.Algorix.TallerKinal.dominio.service.AdministradorService;
import org.Algorix.TallerKinal.dominio.service.ClienteService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component("clienteView")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class ClienteView implements Serializable {
    private final ClienteService clienteService;
    private final AdministradorService administradorService;
    private List<ClienteDto> clientes;
    private ClienteDto selected;
    // Campos unificados
    private String editName;
    private String editEmail;
    private String editPassword;
    private String editLastName; // para creación si se requiere
    // Campos para el login unificado
    private String loginEmail;
    private String loginPassword;
    private ClienteDto currentCliente;
    private boolean loggedIn = false;
    public ClienteView(ClienteService clienteService, AdministradorService administradorService) { this.clienteService = clienteService; this.administradorService = administradorService; }
    @PostConstruct public void init() { refresh(); clearEdits(); }
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
                ClienteDto mod = new ClienteDto(selected.getId_cliente(), editName, editLastName, editEmail, editPassword);
                clienteService.modificarCliente(selected.getId_cliente(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("clientesForm:tablaClientes", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
            this.selected = null; clearEdits();

        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }
    // -------- Inicio de sesión unificado --------
    /**
     * Intenta autenticar como administrador primero; si no, intenta como cliente.
     * Retorna outcome JSF para navegación o null si se queda en la misma página.
     */
    public String login() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        // Intentar admin primero
        try {
            UserAdminDto admin = new UserAdminDto(loginEmail, loginPassword);
            var adminDto = administradorService.iniciarSesion(admin);
            if (adminDto != null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión", "Administrador autenticado."));
                this.loginEmail = ""; this.loginPassword = "";
                redirect("AdministradorWeb.html");
                return null; // navegación manejada por redirect
            }
        } catch (Exception e) {
            // continuar con cliente
        }
        // Intentar cliente
        try {
            UserClienteDto user = new UserClienteDto(loginEmail, loginPassword);
            var clienteDto = clienteService.iniciarSesion(user);
            if (clienteDto != null) {
                this.currentCliente = clienteDto; this.loggedIn = true;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión", "Cliente autenticado."));
                this.loginEmail = ""; this.loginPassword = "";
                redirect("clienteWeb.xhtml");
                return null;
            } else {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Inicio de sesión", "Credenciales incorrectas."));
            }
        } catch (Exception e) {
            String msg = e.getMessage() == null ? "Credenciales inválidas" : e.getMessage();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicio de sesión", msg));
        }
        return null;
    }
    private void redirect(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo redirigir a " + page));
        }
    }
    public void logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.currentCliente = null; this.loggedIn = false; this.loginEmail = ""; this.loginPassword = "";
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout", "Usuario desconectado."));
        System.out.println("[LOGOUT] Usuario desconectado");
    }
    // Getters y setters explícitos para que JSF pueda resolver propiedades en tiempo de ejecución
    public String getLoginEmail() { return this.loginEmail; }
    public void setLoginEmail(String loginEmail) { this.loginEmail = loginEmail; }
    public String getLoginPassword() { return this.loginPassword; }
    public void setLoginPassword(String loginPassword) { this.loginPassword = loginPassword; }
    public ClienteDto getCurrentCliente() { return this.currentCliente; }
    public void setCurrentCliente(ClienteDto currentCliente) { this.currentCliente = currentCliente; }
    public boolean isLoggedIn() { return this.loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
    public void eliminarCliente() {
        if (this.selected == null || this.selected.getId_cliente() == null) return;
        try { clienteService.eliminarCliente(this.selected.getId_cliente()); FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado")); refresh(); PrimeFaces.current().ajax().update("clientesForm:tablaClientes", "growlForm:growlMensajes"); this.selected = null; clearEdits(); }
        catch (Exception e) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar")); }
    }
    public void cancelarCliente() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()"); }
}