package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.service.ProductoInventarioService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("productoInventarioView")
@SessionScope
@Data
public class ProductoInventarioView implements Serializable {
    private final ProductoInventarioService productoInventarioService;
    private List<ProductoInventarioDto> productos;
    private ProductoInventarioDto selected;

    // Campos unificados (crear/editar)
    private Long editIdProveedor;
    private String editName;
    private String editDescription;
    private Long editIdCategoria;
    private String editSpecification;
    private Double editUnitPrice;
    private Integer editCurrentStock;
    private Integer editMinimumStock;
    private Long editIdMarca;
    private LocalDate editEntryDate;

    public ProductoInventarioView(ProductoInventarioService productoInventarioService) { this.productoInventarioService = productoInventarioService; }

    @PostConstruct
    public void init() { refresh(); clearEdits(); }

    public void refresh() { try { this.productos = new ArrayList<>(productoInventarioService.obtenerTodo()); } catch (Exception e) { this.productos = new ArrayList<>(); } }

    private void clearEdits() { this.editIdProveedor = null; this.editName = ""; this.editDescription = ""; this.editIdCategoria = null; this.editSpecification = ""; this.editUnitPrice = null; this.editCurrentStock = null; this.editMinimumStock = null; this.editIdMarca = null; this.editEntryDate = null; }

    public void agregarProductoInventario() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalProductoInventario').show()"); }

    public void prepararEdicionProductoInventario(ProductoInventarioDto p) {
        this.selected = p; clearEdits();
        if (p != null) {
            this.editIdProveedor = p.idProveedor();
            this.editName = p.name();
            this.editDescription = p.description();
            this.editIdCategoria = p.idCategoria();
            this.editSpecification = p.specification();
            this.editUnitPrice = p.unitPrice();
            this.editCurrentStock = p.currentStock();
            this.editMinimumStock = p.minimumStock();
            this.editIdMarca = p.idMarca();
            this.editEntryDate = p.entryDate();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalProductoInventario').show()");
    }

    public void guardarProductoInventario() {
        try {
            if (this.selected == null) {
                ProductoInventarioDto dto = new ProductoInventarioDto(null, editIdProveedor, editName, editDescription, editIdCategoria, editSpecification, editUnitPrice, editCurrentStock, editMinimumStock, editIdMarca, editEntryDate);
                productoInventarioService.guardarProducto(dto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Agregado"));
            } else {
                ModProductoInventarioDto mod = new ModProductoInventarioDto(editIdProveedor, editName, editDescription, editIdCategoria, editSpecification, editUnitPrice, editCurrentStock, editMinimumStock, editIdMarca, editEntryDate);
                productoInventarioService.modificarProducto(selected.id_producto(), mod);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Modificado"));
            }
            refresh();
            PrimeFaces.current().ajax().update("productosForm:tablaProductos", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalProductoInventario').hide()");
            this.selected = null; clearEdits();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
            PrimeFaces.current().ajax().update("growlForm:growlMensajes");
        }
    }

    public void eliminarProductoInventario(ProductoInventarioDto p) {
        if (p == null || p.id_producto() == null) return;
        try {
            productoInventarioService.eliminarProducto(p.id_producto());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("productosForm:tablaProductos", "growlForm:growlMensajes");
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
            PrimeFaces.current().ajax().update("growlForm:growlMensajes");
        }
    }

    public void cancelarProductoInventario() { this.selected = null; clearEdits(); PrimeFaces.current().executeScript("PF('ventanaModalProductoInventario').hide()"); }
}
