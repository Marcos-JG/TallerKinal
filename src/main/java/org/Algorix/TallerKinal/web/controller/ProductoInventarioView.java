package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ModProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.ProductoInventarioDto;
import org.Algorix.TallerKinal.dominio.dto.productoWebDto;
import org.Algorix.TallerKinal.dominio.service.ProductoInventarioService;
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

    // Campos nuevo
    private Long newIdProveedor;
    private String newName;
    private String newDescription;
    private Long newIdCategoria;
    private String newSpecification;
    private Double newUnitPrice;
    private Integer newCurrentStock;
    private Integer newMinimumStock;
    private Long newIdMarca;
    private LocalDate newEntryDate;

    // Edición
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

    public ProductoInventarioView(ProductoInventarioService productoInventarioService) {
        this.productoInventarioService = productoInventarioService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.productos = new ArrayList<>(productoInventarioService.obtenerTodo());
        } catch (Exception e) {
            this.productos = new ArrayList<>();
        }
    }

    public void add() {
        ProductoInventarioDto dto = new ProductoInventarioDto(null, newIdProveedor, newName, newDescription, newIdCategoria, newSpecification, newUnitPrice, newCurrentStock, newMinimumStock, newIdMarca, newEntryDate);
        productoInventarioService.guardarProducto(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(ProductoInventarioDto p) {
        this.selected = p;
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
    }

    public void saveEdit() {
        if (selected == null) return;
        // ModProductoInventarioDto(order: idProveedor, name, description, idCategoria, specification, unitPrice, currentStock, minimumStock, idMarca, entryDate)
        ModProductoInventarioDto mod = new ModProductoInventarioDto(editIdProveedor, editName, editDescription, editIdCategoria, editSpecification, editUnitPrice, editCurrentStock, editMinimumStock, editIdMarca, editEntryDate);
        productoInventarioService.modificarProducto(selected.id_producto(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(ProductoInventarioDto p) {
        if (p == null || p.id_producto() == null) return;
        productoInventarioService.eliminarProducto(p.id_producto());
        refresh();
    }

    public void cancelEdit() { this.selected = null; clearEditForm(); }

    public void clearNewForm() {
        this.newIdProveedor = null; this.newName = ""; this.newDescription = ""; this.newIdCategoria = null; this.newSpecification = ""; this.newUnitPrice = null; this.newCurrentStock = null; this.newMinimumStock = null; this.newIdMarca = null; this.newEntryDate = null;
    }
    public void clearEditForm() {
        this.editName = ""; this.editDescription = ""; this.editIdCategoria = null; this.editSpecification = ""; this.editUnitPrice = null; this.editCurrentStock = null; this.editMinimumStock = null; this.editIdMarca = null; this.editEntryDate = null;
    }
}
