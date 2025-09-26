package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.service.VehiculoService;
import org.Algorix.TallerKinal.dominio.MarcaVehiculo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("vehiculoView")
@SessionScope
@Data
public class VehiculoView implements Serializable {
    private final VehiculoService vehiculoService;
    private List<VehiculoDto> vehiculos;
    private VehiculoDto selected;

    // Nuevos
    private Long newIdCliente;
    private String newPlacas;
    private String newColor;
    private String newModelo;
    private String newMarca;
    private Integer newAno;

    // Edición
    private String editPlacas;
    private String editColor;
    private String editModelo;
    private String editMarca;
    private Integer editAno;

    public VehiculoView(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.vehiculos = new ArrayList<>(vehiculoService.obtenerTodo());
        } catch (Exception e) {
            this.vehiculos = new ArrayList<>();
        }
    }

    public void add() {
        VehiculoDto nuevo = new VehiculoDto(null, newIdCliente, newPlacas, newColor, newModelo, null, newAno);
        vehiculoService.guardarVehiculo(nuevo);
        refresh();
        clearNewForm();
    }

    public void startEdit(VehiculoDto v) {
        this.selected = v;
        if (v != null) {
            this.editPlacas = v.licensePlate();
            this.editColor = v.color();
            this.editModelo = v.model();
            this.editMarca = v.marca() == null ? "" : v.marca().name();
            this.editAno = v.year();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        // ModVehiculoDto expects: color, model, MarcaVehiculo, year, idCliente
        MarcaVehiculo marcaEnum = null;
        try {
            if (editMarca != null && !editMarca.isBlank()) marcaEnum = MarcaVehiculo.valueOf(editMarca.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            marcaEnum = null; // will be validated later
        }
        ModVehiculoDto mod = new ModVehiculoDto(editColor, editModelo, marcaEnum, editAno, selected.idCliente());
        // use licensePlate as identifier for modificarVehiculo (controller expects placas)
        vehiculoService.modificarVehiculo(selected.licensePlate(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(VehiculoDto v) {
        if (v == null || v.id_vehiculo() == null) return;
        vehiculoService.eliminarVehiculo(v.id_vehiculo());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    public void clearNewForm() {
        this.newIdCliente = null;
        this.newPlacas = "";
        this.newColor = "";
        this.newModelo = "";
        this.newMarca = "";
        this.newAno = null;
    }

    public void clearEditForm() {
        this.editPlacas = "";
        this.editColor = "";
        this.editModelo = "";
        this.editMarca = "";
        this.editAno = null;
    }
}
