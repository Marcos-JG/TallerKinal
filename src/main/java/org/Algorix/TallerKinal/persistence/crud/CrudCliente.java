package org.Algorix.TallerKinal.persistence.crud;

import org.Algorix.TallerKinal.persistence.entity.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCliente extends CrudRepository<ClienteEntity,Long> {
    ClienteEntity findFirstByCorreo(String correo);
}
