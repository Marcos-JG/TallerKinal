package org.Algorix.TallerKinal.persistence;

import org.Algorix.TallerKinal.dominio.dto.MecanicoDto;
import org.Algorix.TallerKinal.dominio.dto.ModMecanicoDto;
import org.Algorix.TallerKinal.dominio.exception.MecanicoNoExiste;
import org.Algorix.TallerKinal.dominio.repository.MecanicoRepository;
import org.Algorix.TallerKinal.persistence.crud.CrudMecanico;
import org.Algorix.TallerKinal.persistence.crud.CrudCita;
import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.Algorix.TallerKinal.web.mapper.MecanicoMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MecanicoEntityRepository implements MecanicoRepository {

    private final CrudMecanico crudMecanico;
    private final CrudCita crudCita;
    private final MecanicoMapper mecanicoMapper;
    // lock para evitar inserciones concurrentes en la misma JVM
    private static final Object INSERT_LOCK = new Object();

    public MecanicoEntityRepository(CrudMecanico crudMecanico, CrudCita crudCita, MecanicoMapper mecanicoMapper) {
        this.crudMecanico = crudMecanico;
        this.crudCita = crudCita;
        this.mecanicoMapper = mecanicoMapper;
    }

    @Override
    public List<MecanicoDto> listarMecanico() {
        return this.mecanicoMapper.toDto(this.crudMecanico.findAll());
    }

    @Override
    public MecanicoDto buscarMecanicoPorCodigo(Long codigo) {
        MecanicoEntity mecanicoEntity = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanicoEntity == null) {
            throw new MecanicoNoExiste(codigo);
        }
        return this.mecanicoMapper.toDto(mecanicoEntity);
    }

    @Override
    public void eliminarMecanico(Long codigo) {
        MecanicoEntity mecanicoEntity = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanicoEntity == null) {
            throw new MecanicoNoExiste(codigo);
        }
        // Evitar eliminar si existen citas que referencien a este mecánico
        if (this.crudCita.existsByEmpleadoId(codigo)) {
            throw new DataIntegrityViolationException("No se puede eliminar el mecánico " + codigo + ": existen citas asociadas.");
        }
        this.crudMecanico.deleteById(codigo);
    }

    @Override
    public MecanicoDto editarMecanico(Long codigo, ModMecanicoDto modMecanicoDto) {
        MecanicoEntity mecanico = this.crudMecanico.findById(codigo).orElse(null);
        if (mecanico == null) {
            throw new MecanicoNoExiste(codigo);
        }
        // Evitar duplicados: comprobar si ya existe otro registro con los mismos datos
        String n = modMecanicoDto.name() == null ? "" : modMecanicoDto.name().trim();
        String ln = modMecanicoDto.lastName() == null ? "" : modMecanicoDto.lastName().trim();
        String ph = modMecanicoDto.phone() == null ? "" : modMecanicoDto.phone().trim();
        var posible = this.crudMecanico.findByNormalized(n, ln, ph);
        if (posible.isPresent() && !posible.get().getId_mecanico().equals(codigo)) {
            throw new DataIntegrityViolationException("No se puede editar: ya existe un mecánico con los mismos datos.");
        }
        this.mecanicoMapper.modificarEntityFromDto(modMecanicoDto, mecanico);
        // actualizar uniqueKey antes de guardar
        mecanico.setUniqueKey(buildUniqueKey(n, ln, ph));
        return this.mecanicoMapper.toDto(this.crudMecanico.save(mecanico));
    }

    @Override
    @Transactional
    public MecanicoDto guardarMecanico(MecanicoDto mecanicoDto) {
        // Evitar duplicados por nombre+apellido+telefono (normalizados)
        if (mecanicoDto != null) {
            String n = mecanicoDto.name() == null ? "" : mecanicoDto.name().trim();
            String ln = mecanicoDto.lastName() == null ? "" : mecanicoDto.lastName().trim();
            String ph = mecanicoDto.phone() == null ? "" : mecanicoDto.phone().trim();
            if (this.crudMecanico.existsByNormalized(n, ln, ph)) {
                throw new DataIntegrityViolationException("No se puede crear: ya existe un mecánico con los mismos datos.");
            }
            // reemplazar valores en DTO por versión recortada
            mecanicoDto = new MecanicoDto(mecanicoDto.idMecanico(), n, ln, ph);
        }
        // doble verificación dentro de lock/transacción para evitar condiciones de carrera
        synchronized (INSERT_LOCK) {
            String n = mecanicoDto == null ? "" : (mecanicoDto.name() == null ? "" : mecanicoDto.name().trim());
            String ln = mecanicoDto == null ? "" : (mecanicoDto.lastName() == null ? "" : mecanicoDto.lastName().trim());
            String ph = mecanicoDto == null ? "" : (mecanicoDto.phone() == null ? "" : mecanicoDto.phone().trim());
            if (this.crudMecanico.existsByNormalized(n, ln, ph)) {
                // devolver DTO existente en lugar de insertar
                var existing = this.crudMecanico.findByNormalized(n, ln, ph).orElse(null);
                if (existing != null) {
                    return this.mecanicoMapper.toDto(existing);
                }
                throw new DataIntegrityViolationException("No se puede crear: ya existe un mecánico con los mismos datos.");
            }
            MecanicoEntity mecanico = this.mecanicoMapper.toEntity(mecanicoDto);
            // establecer uniqueKey normalizada y consistente
            mecanico.setUniqueKey(buildUniqueKey(mecanico.getNombre(), mecanico.getApellido(), mecanico.getTelefono()));
            try {
                this.crudMecanico.save(mecanico);
            } catch (DataIntegrityViolationException ex) {
                // Traduce excepción de BD a mensaje amigable (por si la constraint DB evita duplicados concurrentes)
                throw new DataIntegrityViolationException("No se puede crear: ya existe un mecánico con los mismos datos.");
            }
            return this.mecanicoMapper.toDto(mecanico);
        }
    }

    // Construye clave única normalizada: lower(trim(nombre))+"|"+lower(trim(apellido))+"|"+lower(trim(telefono))
    private String buildUniqueKey(String nombre, String apellido, String telefono) {
        String n = nombre == null ? "" : nombre.trim().toLowerCase();
        String ln = apellido == null ? "" : apellido.trim().toLowerCase();
        String ph = telefono == null ? "" : telefono.trim().toLowerCase();
        return n + "|" + ln + "|" + ph;
    }
}
