package com.InvTierradentro.invtierradentro.aplication.Interfaces;

import com.InvTierradentro.invtierradentro.aplication.models.Dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<UserDao, Long> {
     public UserDao findByUsername(String username);
}
