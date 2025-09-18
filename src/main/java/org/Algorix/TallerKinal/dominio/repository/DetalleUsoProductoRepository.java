package org.Algorix.TallerKinal.dominio.repository;

import org.Algorix.TallerKinal.persistence.entity.DetalleUsoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleUsoProductoRepository extends JpaRepository<DetalleUsoProductoEntity, Long> {
    void deleteByProductoInventarioId(Long productoInventarioId);

}
