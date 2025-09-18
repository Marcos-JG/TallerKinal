package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.MarcaProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMarcaProducto extends CrudRepository<MarcaProductoEntity,Long> {
}
