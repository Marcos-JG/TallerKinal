package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.MarcaProductoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMarcaProductoDto;
import org.Algorix.TallerKinal.dominio.service.MarcaProductoService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("marcaProductoView")
@SessionScope
@Data
public class MarcaProductoView implements Serializable {
    private final MarcaProductoService marcaProductoService;
    private List<MarcaProductoDto> marcas;
    private MarcaProductoDto selected;

    private String newName;
    private String editName;

    public MarcaProductoView(MarcaProductoService marcaProductoService) {
        this.marcaProductoService = marcaProductoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            List<MarcaProductoDto> list = marcaProductoService.obtenerTodo();
            this.marcas = list == null ? new ArrayList<>() : new ArrayList<>(list);
        } catch (Exception e) {
            this.marcas = new ArrayList<>();
        }
    }

    public void add() {
        MarcaProductoDto dto = new MarcaProductoDto(null, newName);
        marcaProductoService.guardarMarca(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(MarcaProductoDto m) {
        this.selected = m;
        if (m != null) this.editName = m.name();
    }

    public void saveEdit() {
        if (selected == null) return;
        ModMarcaProductoDto mod = new ModMarcaProductoDto(editName);
        marcaProductoService.modificarMarca(selected.id_marca(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(MarcaProductoDto m) {
        if (m == null || m.id_marca() == null) return;
        marcaProductoService.eliminarMarca(m.id_marca());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    public void clearNewForm() { this.newName = ""; }
    public void clearEditForm() { this.editName = ""; }
}
