package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.MecanicoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CrudMecanico extends CrudRepository<MecanicoEntity,Long> {
    // Comprueba existencia por nombre+apellido+telefono para evitar duplicados
    boolean existsByNombreAndApellidoAndTelefono(String nombre, String apellido, String telefono);

    // Búsquedas normalizadas (ignorando mayúsculas y espacios iniciales/finales)
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MecanicoEntity m "
            + "WHERE LOWER(TRIM(m.nombre)) = LOWER(TRIM(:nombre)) "
            + "AND LOWER(TRIM(m.apellido)) = LOWER(TRIM(:apellido)) "
            + "AND LOWER(TRIM(m.telefono)) = LOWER(TRIM(:telefono))")
    boolean existsByNormalized(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("telefono") String telefono);

    @Query("SELECT m FROM MecanicoEntity m "
            + "WHERE LOWER(TRIM(m.nombre)) = LOWER(TRIM(:nombre)) "
            + "AND LOWER(TRIM(m.apellido)) = LOWER(TRIM(:apellido)) "
            + "AND LOWER(TRIM(m.telefono)) = LOWER(TRIM(:telefono))")
    Optional<MecanicoEntity> findByNormalized(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("telefono") String telefono);
}
