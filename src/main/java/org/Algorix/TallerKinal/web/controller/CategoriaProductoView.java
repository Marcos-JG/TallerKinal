// ...existing code...
package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.dominio.service.CategoriaProductoService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("categoriaProductoView")
@SessionScope
@Data
public class CategoriaProductoView{
    private final CategoriaProductoService categoriaProductoService;
    private List<CategoriaProductoDto> categorias;
    private CategoriaProductoDto selected;

    private String editName;

    public CategoriaProductoView(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearEdits();
    }

    public void refresh() {
        try {
            this.categorias = new ArrayList<>(categoriaProductoService.obtenerTodo());
        } catch (Exception e) {
            this.categorias = new ArrayList<>();
        }
    }

    private void clearEdits(){ this.editName = ""; }

    // Métodos patrón modal estilo Proveedor
    public void agregarCategoriaProducto() {
        this.selected = null;
        clearEdits();
        PrimeFaces.current().executeScript("PF('ventanaModalCategoriaProducto').show()");
    }
    public void prepararEdicionCategoriaProducto(CategoriaProductoDto c) {
        this.selected = c;
        clearEdits();
        if (c != null) this.editName = c.name();
        PrimeFaces.current().executeScript("PF('ventanaModalCategoriaProducto').show()");
    }
    public void guardarCategoriaProducto() {
        try {
            if (this.selected == null) {
                CategoriaProductoDto dto = new CategoriaProductoDto(null, editName);
                categoriaProductoService.guardarCategoria(dto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoría Agregada"));
            } else {
                ModCategoriaDto mod = new ModCategoriaDto(editName);
                categoriaProductoService.modificarCategoria(selected.id_categoria(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoría Modificada"));
            }
            refresh();
            PrimeFaces.current().ajax().update("categoriasForm:tablaCategorias", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalCategoriaProducto').hide()");
            this.selected = null;
            clearEdits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }
    public void eliminarCategoriaProducto(CategoriaProductoDto c) {
        if (c == null || c.id_categoria() == null) return;
        try {
            categoriaProductoService.eliminarCategoria(c.id_categoria());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoría Eliminada"));
            refresh();
            PrimeFaces.current().ajax().update("categoriasForm:tablaCategorias", "growlForm:growlMensajes");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }
    public void cancelarCategoriaProducto() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalCategoriaProducto').hide()"); }
}
