package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.MarcaVehiculo;
import org.Algorix.TallerKinal.dominio.dto.ModVehiculoDto;
import org.Algorix.TallerKinal.dominio.dto.VehiculoDto;
import org.Algorix.TallerKinal.dominio.exception.MarcaVehiculoNoExiste;
import org.Algorix.TallerKinal.dominio.exception.VehiculoDuplicadoPlaca;
import org.Algorix.TallerKinal.dominio.exception.VehiculoNoExiste;
import org.Algorix.TallerKinal.dominio.service.VehiculoService;
import org.primefaces.PrimeFaces;
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

    // Campos de edición / creación unificados (patrón modal)
    private Long editIdCliente;
    private String editPlacas;
    private String editColor;
    private String editModelo;
    private String editMarca;
    private Integer editAno;

    public VehiculoView(VehiculoService vehiculoService) { this.vehiculoService = vehiculoService; }

    @PostConstruct
    public void init() { refresh(); clearEditForm(); }

    public void refresh() { try { this.vehiculos = new ArrayList<>(vehiculoService.obtenerTodo()); } catch (Exception e) { this.vehiculos = new ArrayList<>(); } }
    public void clearEditForm() { this.editIdCliente = null; this.editPlacas = ""; this.editColor = ""; this.editModelo = ""; this.editMarca = ""; this.editAno = null; }
    public MarcaVehiculo[] getListaMarcas() { return MarcaVehiculo.values(); }

    public void agregarVehiculo() { this.selected = null; clearEditForm(); PrimeFaces.current().executeScript("PF('ventanaModalVehiculo').show()"); }
    public void prepararEdicionVehiculo(VehiculoDto v) {
        this.selected = v; clearEditForm();
        if (v != null) {
            this.editIdCliente = v.idCliente();
            this.editPlacas = v.licensePlate();
            this.editColor = v.color();
            this.editModelo = v.model();
            this.editMarca = v.marca() == null ? "" : v.marca().name();
            this.editAno = v.year();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalVehiculo').show()");
    }
    public void guardarVehiculo() {
        try {
            MarcaVehiculo marcaEnum = null;
            if (editMarca != null && !editMarca.isBlank()) {
                try { marcaEnum = MarcaVehiculo.valueOf(editMarca.trim().toUpperCase()); }
                catch (IllegalArgumentException ex) { addError("Marca inválida"); return; }
            }
            if (marcaEnum == null) { addError("Debe seleccionar una marca"); return; }
            if (editPlacas == null || editPlacas.isBlank()) { addError("Placas requeridas"); return; }
            if (editIdCliente == null) { addError("ID Cliente requerido"); return; }
            if (this.selected == null) {
                VehiculoDto nuevo = new VehiculoDto(null, editIdCliente, editPlacas.trim(), editColor, editModelo, marcaEnum, editAno);
                vehiculoService.guardarVehiculo(nuevo);
                addInfo("Vehículo Agregado");
            } else {
                ModVehiculoDto mod = new ModVehiculoDto(editColor, editModelo, marcaEnum, editAno, editIdCliente);
                vehiculoService.modificarVehiculo(selected.licensePlate(), mod);
                addInfo("Vehículo Modificado");
            }
            refresh();
            PrimeFaces.current().ajax().update("vehiculosForm:tablaVehiculos", "growlForm:growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalVehiculo').hide()");
            this.selected = null;
        } catch (Exception e) {
            if (e instanceof VehiculoDuplicadoPlaca) {
                addError("Ya existe un vehículo con esas placas");
            } else if (e instanceof MarcaVehiculoNoExiste) {
                addError("La marca indicada no es válida");
            } else if (e instanceof VehiculoNoExiste) {
                addError("El vehículo no existe (posible edición concurrente)");
            } else {
                addError("No se pudo guardar: " + e.getMessage());
            }
            PrimeFaces.current().ajax().update("growlForm:growlMensajes");
        }
    }
    public void eliminarVehiculo(VehiculoDto v) {
        if (v == null || v.id_vehiculo() == null) return;
        try {
            vehiculoService.eliminarVehiculo(v.id_vehiculo());
            addInfo("Vehículo Eliminado");
            refresh();
            PrimeFaces.current().ajax().update("vehiculosForm:tablaVehiculos", "growlForm:growlMensajes");
        } catch (Exception e) {
            if (e instanceof VehiculoNoExiste) {
                addError("El vehículo ya no existe");
            } else {
                addError("No se pudo eliminar");
            }
            PrimeFaces.current().ajax().update("growlForm:growlMensajes");
        }
    }
    public void cancelarVehiculo() { this.selected = null; PrimeFaces.current().executeScript("PF('ventanaModalVehiculo').hide()"); }

    private void addInfo(String msg) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg)); }
    private void addError(String msg) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg)); }
}
