package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.persistence.entity.DetalleUsoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DetalleUsoProductoRepository extends JpaRepository<DetalleUsoProductoEntity, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM DetalleUsoProductoEntity d WHERE d.productoInventario.id_producto = :id")
    void deleteByProductoInventarioId(@Param("id") Long id);

}
