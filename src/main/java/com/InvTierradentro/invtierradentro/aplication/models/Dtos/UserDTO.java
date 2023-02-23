package com.InvTierradentro.invtierradentro.aplication.models.Dtos;

import com.InvTierradentro.invtierradentro.aplication.models.Dao.PersonasDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dao.UserDao;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class UserDTO {
    private String username;

    private String password;


    private Boolean status;

    private PersonasDao personasDao;


    @Override
    public String toString() {
        return "UserDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password='" + personasDao + '\'' +
                ", status=" + status +
                '}';
    }

    public UserDTO() {
    }

    public UserDTO( String username, String password, Boolean status, PersonasDao personasDao) {
        this.username = username;
        this.password = password;
        this.status=status;
        this.personasDao=personasDao;
    }

    public  UserDTO fromDomain(UserDao userDao){
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(userDao.getUsername());
        userDTO.setPassword(userDao.getPassword());
        userDTO.setStatus(userDao.getStatus());
        userDTO.setPersonasDao(userDao.getPersonasDao());
        return userDTO;
    }

    public UserDao fromDAO(UserDTO userDTO){
        UserDao userDao= new UserDao();
        userDao.setUsername(userDTO.getUsername());
        userDao.setPassword(userDTO.getPassword());
        userDao.setStatus(userDTO.getStatus());
        userDao.setPersonasDao(userDTO.getPersonasDao());
        return userDao;
    }

}
