package org.Algorix.TallerKinal.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.Algorix.TallerKinal.dominio.dto.CitaDto;
import org.Algorix.TallerKinal.dominio.dto.ModCitaDto;
import org.Algorix.TallerKinal.dominio.service.CitaService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("citaView")
@SessionScope
@Data
public class CitaView implements Serializable {
    private final CitaService citaService;
    private List<CitaDto> citas;
    private CitaDto selected;

    // Campos nuevo
    private LocalDate newAppointmentDate;
    private Long newIdEmpleado;
    private Long newIdCliente;
    private String newAppointmentType;
    private Long newIdVehiculo;
    private String newStatus;
    private String newComments;

    // Campos edición
    private LocalDate editAppointmentDate;
    private String editStatus;
    private String editComments;

    public CitaView(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostConstruct
    public void init() {
        refresh();
        clearNewForm();
        clearEditForm();
    }

    public void refresh() {
        try {
            this.citas = new ArrayList<>(citaService.obtenerTodo());
        } catch (Exception e) {
            this.citas = new ArrayList<>();
        }
    }

    public void add() {
        CitaDto nuevo = new CitaDto(null, newAppointmentDate, newIdEmpleado, newIdCliente, newAppointmentType, newIdVehiculo, newStatus, newComments);
        citaService.guardarCita(nuevo);
        refresh();
        clearNewForm();
    }

    public void startEdit(CitaDto c) {
        this.selected = c;
        if (c != null) {
            this.editAppointmentDate = c.appointmentDate();
            this.editStatus = c.status();
            this.editComments = c.comments();
        }
    }

    public void saveEdit() {
        if (selected == null) return;
        // ModCitaDto(order: idEmpleado, appointmentDate, appointmentType, idVehiculo, status, comments)
        ModCitaDto mod = new ModCitaDto(selected.idEmpleado(), editAppointmentDate, selected.appointmentType(), selected.idVehiculo(), editStatus, editComments);
        citaService.modificarCita(selected.id_cita(), mod);
        refresh();
        clearEditForm();
        this.selected = null;
    }

    public void delete(CitaDto c) {
        if (c == null || c.id_cita() == null) return;
        citaService.eliminarCita(c.id_cita());
        refresh();
    }

    public void cancelEdit() {
        this.selected = null;
        clearEditForm();
    }

    public void clearNewForm() {
        this.newAppointmentDate = null;
        this.newIdEmpleado = null;
        this.newIdCliente = null;
        this.newAppointmentType = null;
        this.newIdVehiculo = null;
        this.newStatus = null;
        this.newComments = null;
    }

    public void clearEditForm() {
        this.editAppointmentDate = null;
        this.editStatus = null;
        this.editComments = null;
    }
}
