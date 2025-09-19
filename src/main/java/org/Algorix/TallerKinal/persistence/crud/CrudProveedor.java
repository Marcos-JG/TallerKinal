package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.ProveedorEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudProveedor extends CrudRepository<ProveedorEntity,Long> {
}
