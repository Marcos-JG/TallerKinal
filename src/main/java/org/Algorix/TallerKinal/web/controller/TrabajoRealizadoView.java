package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ModTrabajoRealozadoDto;
import org.Algorix.TallerKinal.dominio.dto.TrabajoRealizadoDto;
import org.Algorix.TallerKinal.dominio.service.TrabajoRealizadoService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("trabajoRealizadoView")
@SessionScope
@Data
public class TrabajoRealizadoView implements Serializable {
    private final TrabajoRealizadoService trabajoRealizadoService;
    private List<TrabajoRealizadoDto> trabajos;
    private TrabajoRealizadoDto selected;

    // Nuevos
    private Long newIdCita;
    private String newDescription;
    private BigDecimal newLaborCost;
    private BigDecimal newTotalWork;

    // Edición
    private String editDescription;
    private BigDecimal editLaborCost;
    private BigDecimal editTotalWork;

    public TrabajoRealizadoView(TrabajoRealizadoService trabajoRealizadoService) {
        this.trabajoRealizadoService = trabajoRealizadoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.trabajos = new ArrayList<>(trabajoRealizadoService.listarTrabajoRealizados());
        } catch (Exception e) {
            this.trabajos = new ArrayList<>();
        }
    }

    public void add() {
        TrabajoRealizadoDto dto = new TrabajoRealizadoDto(null, newIdCita, newDescription, newLaborCost, newTotalWork);
        trabajoRealizadoService.guardarTrabajo(dto);
        refresh();
        clearNewForm();
    }

    public void startEdit(TrabajoRealizadoDto t) {
        this.selected = t;
        if (t != null) {
            this.editDescription = t.description();
            this.editLaborCost = t.laborCost();
            this.editTotalWork = t.totalWork();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        ModTrabajoRealozadoDto mod = new ModTrabajoRealozadoDto(editDescription, editLaborCost, editTotalWork);
        trabajoRealizadoService.modificarTrabajo(selected.id_trabajo(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(TrabajoRealizadoDto t) {
        if (t == null || t.id_trabajo() == null) return;
        trabajoRealizadoService.eliminarTrabajo(t.id_trabajo());
        refresh();
    }

    public void cancelEdit() { this.selected = null; clearEditForm(); }

    public void clearNewForm() { this.newIdCita = null; this.newDescription = ""; this.newLaborCost = null; this.newTotalWork = null; }
    public void clearEditForm() { this.editDescription = ""; this.editLaborCost = null; this.editTotalWork = null; }
}

