package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.AdministradorEntity;
import org.Algorix.TallerKinal.persistence.entity.CategoriaProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCategoria extends CrudRepository<CategoriaProductoEntity,Long> {
}
