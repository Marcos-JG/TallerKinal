package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.AdministradorEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudAdministrador  extends CrudRepository<AdministradorEntity,Long> {
}
