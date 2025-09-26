// ...existing code...
package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.CategoriaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModCategoriaDto;
import org.Algorix.TallerKinal.dominio.service.CategoriaProductoService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("categoriaProductoView")
@SessionScope
@Data
public class CategoriaProductoView implements Serializable {
    private final CategoriaProductoService categoriaProductoService;
    private List<CategoriaProductoDto> categorias;
    private CategoriaProductoDto selected;

    private String newName;
    private String editName;

    public CategoriaProductoView(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.categorias = new ArrayList<>(categoriaProductoService.obtenerTodo());
        } catch (Exception e) {
            this.categorias = new ArrayList<>();
        }
    }

    public void add() {
        CategoriaProductoDto dto = new CategoriaProductoDto(null, newName);
        categoriaProductoService.guardarCategoria(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(CategoriaProductoDto c) {
        this.selected = c;
        if (c != null) this.editName = c.name();
    }

    public void saveEdit() {
        if (selected == null) return;
        ModCategoriaDto mod = new ModCategoriaDto(editName);
        categoriaProductoService.modificarCategoria(selected.id_categoria(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(CategoriaProductoDto c) {
        if (c == null || c.id_categoria() == null) return;
        categoriaProductoService.eliminarCategoria(c.id_categoria());
        refresh();
    }

    public void cancelEdit() { this.selected = null; clearEditForm(); }
    public void clearNewForm() { this.newName = ""; }
    public void clearEditForm() { this.editName = ""; }
}
// ...existing code...
