package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("marcaProductoView")
@SessionScope
@Data
public class MarcaProductoView {
    private final MarcaProductoService marcaProductoService;
    private List<MarcaProductoDto> marcas;
    private MarcaProductoDto selected;

    private String editName;

    public MarcaProductoView(MarcaProductoService marcaProductoService) { this.marcaProductoService = marcaProductoService; }

    @PostConstruct
    public void init() { refresh(); clearEdits(); }

    public void refresh() {
        try { List<MarcaProductoDto> list = marcaProductoService.obtenerTodo(); this.marcas = list == null ? new ArrayList<>() : new ArrayList<>(list); }
        catch (Exception e){ this.marcas = new ArrayList<>(); }
    }

    private void clearEdits(){ this.editName = ""; }

    public void agregarMarcaProducto(){ this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalMarcaProducto').show()"); }

    public void prepararEdicionMarcaProducto(MarcaProductoDto m){ this.selected = m; clearEdits(); if(m!=null) this.editName = m.name(); PrimeFaces.current().executeScript("PF('ventanaModalMarcaProducto').show()"); }

    public void guardarMarcaProducto(){
        try {
            if(this.selected == null){
                MarcaProductoDto dto = new MarcaProductoDto(null, editName);
                marcaProductoService.guardarMarca(dto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca Agregada"));
            } else {
                ModMarcaProductoDto mod = new ModMarcaProductoDto(editName);
                marcaProductoService.modificarMarca(selected.id_marca(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca Modificada"));
            }
            refresh();
            PrimeFaces.current().ajax().update("marcasForm:tablaMarcas", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalMarcaProducto').hide()");
            this.selected = null; clearEdits();
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarMarcaProducto(MarcaProductoDto m){
        if(m==null || m.id_marca()==null) return;
        try { marcaProductoService.eliminarMarca(m.id_marca()); FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca Eliminada")); refresh(); PrimeFaces.current().ajax().update("marcasForm:tablaMarcas", "growlForm:growlMensajes"); }
        catch (Exception e){ FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar")); }
    }

    public void cancelarMarcaProducto(){ this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalMarcaProducto').hide()"); }
}
