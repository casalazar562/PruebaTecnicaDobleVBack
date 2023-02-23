package com.InvTierradentro.invtierradentro.aplication.Services;



import com.InvTierradentro.invtierradentro.aplication.Interfaces.IUPersonaDAO;
import com.InvTierradentro.invtierradentro.aplication.Interfaces.IUserDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dao.PersonasDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dao.UserDao;
import com.InvTierradentro.invtierradentro.aplication.models.Dtos.PersonaDTO;
import com.InvTierradentro.invtierradentro.aplication.models.Dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@CrossOrigin(origins = "/*")
public class UsuarioService implements UserDetailsService {

    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IUPersonaDAO iuPersonaDAO;
    private Logger logger= LoggerFactory.getLogger(UsuarioService.class) ;

    Map<String, Integer> mensaje = new HashMap<>();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDao userDao=iUserDao.findByUsername(username);
            if(userDao==null){
                logger.error("Error en el login: No Existe el usuario en el sistema");
                throw  new UsernameNotFoundException("Error en el login: No Existe el usuario en el sistema");
            }
            List<GrantedAuthority> authorities=userDao.getRoles().stream()
                .map(roleDao -> new SimpleGrantedAuthority(roleDao.getDESCRIPTION()))
                .peek(simpleGrantedAuthority -> logger.info("Role: "+ simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());
                logger.info("Usuario autenticado: " + username);
        return new User(userDao.getUsername(),userDao.getPassword(),userDao.getStatus(),true,true,true,authorities);
    }catch (NullPointerException e) {
            String error = "Error en el login, no existe el usuario '" + username + "' en el sistema" + e;
            logger.error(error);

            throw new UsernameNotFoundException(error);
        }
    }


    @Transactional
    public UserDTO findByUsername(String username){
        UserDao userDao=iUserDao.findByUsername(username);
        UserDTO userDTO= new UserDTO();
        userDTO=userDTO.fromDomain(userDao);
        return userDTO ;
    }


    @Transactional(readOnly = true)
    public ResponseEntity SaveUser(PersonaDTO personaDTO){
        if(personaDTO==null){
            mensaje.put("Guardado", 0);
            return new ResponseEntity(mensaje, HttpStatus.IM_USED);
        }
            String password=passwordEncoder.encode(personaDTO.getUserDTO().getPassword());
            personaDTO.getUserDTO().setPassword(password);
            PersonasDao personasDao=personaDTO.fromDAO(personaDTO);;
            personasDao.setCreationDate(LocalDateTime.now());
            iuPersonaDAO.save(personasDao);
            PersonasDao personasDao1= iuPersonaDAO.findByidentification(personaDTO.getIdentification());
            UserDTO userDTO=personaDTO.getUserDTO();
            UserDao userDao=userDTO.fromDAO(userDTO);
            userDao.setCreationDate(LocalDateTime.now());
            userDao.setPersonasDao(personasDao1);
            iUserDao.save(userDao);
            mensaje.put("Guardado", 1);
        return new ResponseEntity(mensaje, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public List<PersonaDTO> findall(){
        List<PersonaDTO> personaDTOS=new ArrayList<>();
        PersonaDTO personaadd=new PersonaDTO();
        List<PersonasDao> personas = (List<PersonasDao>) iuPersonaDAO.findAll();
        for(int i=0;i<personas.size();i++){
            personaDTOS.add(personaadd.fromdomain(personas.get(i)));
        }
        return personaDTOS;
    }
}
