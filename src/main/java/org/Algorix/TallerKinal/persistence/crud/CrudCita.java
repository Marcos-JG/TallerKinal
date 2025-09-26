package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.CitaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CrudCita extends CrudRepository<CitaEntity,Long> {
    // Comprueba si existen citas relacionadas con el mecánico usando una consulta JPQL explícita
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CitaEntity c WHERE c.empleado.id_mecanico = :id")
    boolean existsByEmpleadoId(@Param("id") Long idMecanico);
}
