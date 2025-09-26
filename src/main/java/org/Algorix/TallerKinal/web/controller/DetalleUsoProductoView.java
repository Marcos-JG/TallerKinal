package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.service.DetalleUsoProductoService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("detalleUsoProductoView")
@SessionScope
@Data
public class DetalleUsoProductoView {
    private final DetalleUsoProductoService detalleUsoProductoService;
    private List<DetalleUsoProductoDto> detalles;
    private DetalleUsoProductoDto selected;

    // Campos unificados
    private Long editIdTrabajoRealizado; // se llena sólo al crear
    private Long editIdProductoInventario; // se llena solo al crear
    private Integer editUsedQuantity;
    private BigDecimal editUnitPrice;
    private BigDecimal editSubtotal;

    public DetalleUsoProductoView(DetalleUsoProductoService detalleUsoProductoService) { this.detalleUsoProductoService = detalleUsoProductoService; }

    @PostConstruct public void init(){ refresh(); clearEdits(); }

    public void refresh(){ try { this.detalles = new ArrayList<>(detalleUsoProductoService.obtenerTodo()); } catch (Exception e){ this.detalles = new ArrayList<>(); } }

    private void clearEdits(){ this.editIdTrabajoRealizado = null; this.editIdProductoInventario = null; this.editUsedQuantity = null; this.editUnitPrice = null; this.editSubtotal = null; }

    // Métodos patrón modal
    public void agregarDetalleUsoProducto(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalDetalleUsoProducto').show()"); }
    public void prepararEdicionDetalleUsoProducto(DetalleUsoProductoDto d){ this.selected=d; clearEdits(); if(d!=null){ this.editUsedQuantity=d.usedQuantity(); this.editUnitPrice=d.unitPrice(); this.editSubtotal=d.subtotal(); this.editIdTrabajoRealizado=d.idTrabajoRealizado(); this.editIdProductoInventario=d.idProductoInventario(); } PrimeFaces.current().executeScript("PF('ventanaModalDetalleUsoProducto').show()"); }
    public void guardarDetalleUsoProducto(){ try { if(this.selected==null){ DetalleUsoProductoDto dto = new DetalleUsoProductoDto(null, editIdTrabajoRealizado, editIdProductoInventario, editUsedQuantity, editUnitPrice, editSubtotal); detalleUsoProductoService.guardarDetalle(dto); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Detalle Agregado")); } else { ModDetalleUsoProductoDto mod = new ModDetalleUsoProductoDto(selected.idTrabajoRealizado(), selected.idProductoInventario(), editUsedQuantity, editUnitPrice, editSubtotal); detalleUsoProductoService.modificarDetalle(selected.id_detalle_uso_producto(), mod); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Detalle Modificado")); } refresh(); PrimeFaces.current().ajax().update("detallesForm:tablaDetalles", "growlForm:growlMensajes"); PrimeFaces.current().executeScript("PF('ventanaModalDetalleUsoProducto').hide()"); this.selected=null; clearEdits(); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo guardar")); } }
    public void eliminarDetalleUsoProducto(DetalleUsoProductoDto d){ if(d==null|| d.id_detalle_uso_producto()==null) return; try { detalleUsoProductoService.eliminarDetalle(d.id_detalle_uso_producto()); FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Detalle Eliminado")); refresh(); PrimeFaces.current().ajax().update("detallesForm:tablaDetalles", "growlForm:growlMensajes"); } catch(Exception e){ FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","No se pudo eliminar")); } }
    public void cancelarDetalleUsoProducto(){ this.selected=null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalDetalleUsoProducto').hide()"); }
}
