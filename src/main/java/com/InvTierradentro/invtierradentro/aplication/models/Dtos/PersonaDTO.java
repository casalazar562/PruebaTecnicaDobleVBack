package com.InvTierradentro.invtierradentro.aplication.models.Dtos;

import com.InvTierradentro.invtierradentro.aplication.models.Dao.PersonasDao;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class PersonaDTO {
    private String names;
    private String last_names;
    private String identification;
    private String email;
    private String tipoId;
    private String names_column;
    private String identification_column;

    private UserDTO userDTO;

    public PersonaDTO(String names, String last_names, String identification, String email, String tipoId, String names_column, String identification_column, UserDTO userDTO) {
        this.names = names;
        this.last_names = last_names;
        this.identification = identification;
        this.email = email;
        this.tipoId= tipoId;
        this.names_column = names_column;
        this.identification_column = identification_column;
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "names='" + names + '\'' +
                ", last_names='" + last_names + '\'' +
                ", identification='" + identification + '\'' +
                ", email='" + email + '\'' +
                ", tipoId='" + tipoId + '\'' +
                ", names_column='" + names_column + '\'' +
                ", identification_column='" + identification_column + '\'' +
                ", userDTO=" + userDTO +
                '}';
    }

    public PersonaDTO(){

    }

    public PersonaDTO fromdomain(PersonasDao personasDao){
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setNames(personasDao.getNames());
        personaDTO.setLast_names(personasDao.getLast_names());
        personaDTO.setIdentification(personasDao.getIdentification());
        personaDTO.setEmail(personasDao.getEmail());
        personaDTO.setTipoId(personasDao.getTipoId());
        personaDTO.setNames_column(personasDao.getNames_column());
        personaDTO.setIdentification_column(personasDao.getIdentification_column());
        return personaDTO;
    }

    public PersonasDao fromDAO(PersonaDTO personaDTO){
        PersonasDao personasDao=new PersonasDao();
        personasDao.setNames(personaDTO.getNames());
        personasDao.setLast_names(personaDTO.getLast_names());
        personasDao.setIdentification(personaDTO.getIdentification());
        personasDao.setEmail(personaDTO.getEmail());
        personasDao.setTipoId(personaDTO.getTipoId());
        personasDao.setNames_column(personaDTO.getNames_column());
        personasDao.setIdentification_column(personaDTO.getIdentification_column());
        return personasDao;
    }
}
