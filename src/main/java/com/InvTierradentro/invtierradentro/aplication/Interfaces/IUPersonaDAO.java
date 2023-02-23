package com.InvTierradentro.invtierradentro.aplication.Interfaces;

import com.InvTierradentro.invtierradentro.aplication.models.Dao.PersonasDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUPersonaDAO extends CrudRepository<PersonasDao, Long> {

    PersonasDao findByidentification(String identification);

}
