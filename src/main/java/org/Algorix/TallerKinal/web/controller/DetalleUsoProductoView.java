package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.DetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModDetalleUsoProductoDto;
import org.Algorix.TallerKinal.dominio.service.DetalleUsoProductoService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("detalleUsoProductoView")
@SessionScope
@Data
public class DetalleUsoProductoView implements Serializable {
    private final DetalleUsoProductoService detalleUsoProductoService;
    private List<DetalleUsoProductoDto> detalles;
    private DetalleUsoProductoDto selected;

    private Long newIdTrabajoRealizado;
    private Long newIdProductoInventario;
    private Integer newUsedQuantity;
    private BigDecimal newUnitPrice;
    private BigDecimal newSubtotal;

    private Integer editUsedQuantity;
    private BigDecimal editUnitPrice;
    private BigDecimal editSubtotal;

    public DetalleUsoProductoView(DetalleUsoProductoService detalleUsoProductoService) {
        this.detalleUsoProductoService = detalleUsoProductoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.detalles = new ArrayList<>(detalleUsoProductoService.obtenerTodo());
        } catch (Exception e) {
            this.detalles = new ArrayList<>();
        }
    }

    public void add() {
        DetalleUsoProductoDto dto = new DetalleUsoProductoDto(null, newIdTrabajoRealizado, newIdProductoInventario, newUsedQuantity, newUnitPrice, newSubtotal);
        detalleUsoProductoService.guardarDetalle(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(DetalleUsoProductoDto d) {
        this.selected = d;
        if (d != null) {
            this.editUsedQuantity = d.usedQuantity();
            this.editUnitPrice = d.unitPrice();
            this.editSubtotal = d.subtotal();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        // ModDetalleUsoProductoDto(order: idTrabajoRealizado, idProductoInventario, usedQuantity, unitPrice, subtotal)
        ModDetalleUsoProductoDto mod = new ModDetalleUsoProductoDto(selected.idTrabajoRealizado(), selected.idProductoInventario(), editUsedQuantity, editUnitPrice, editSubtotal);
        detalleUsoProductoService.modificarDetalle(selected.id_detalle_uso_producto(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(DetalleUsoProductoDto d) {
        if (d == null || d.id_detalle_uso_producto() == null) return;
        detalleUsoProductoService.eliminarDetalle(d.id_detalle_uso_producto());
        refresh();
    }

    public void cancelEdit() { this.selected = null; clearEditForm(); }

    public void clearNewForm() { this.newIdTrabajoRealizado = null; this.newIdProductoInventario = null; this.newUsedQuantity = null; this.newUnitPrice = null; this.newSubtotal = null; }
    public void clearEditForm() { this.editUsedQuantity = null; this.editUnitPrice = null; this.editSubtotal = null; }
}
