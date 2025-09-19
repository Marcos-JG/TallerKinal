package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.ProductoInventarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudProducto extends CrudRepository<ProductoInventarioEntity,Long> {
}
