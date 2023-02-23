package com.InvTierradentro.invtierradentro.aplication.controllers;


import com.InvTierradentro.invtierradentro.aplication.Services.UsuarioService;
import com.InvTierradentro.invtierradentro.aplication.models.Dtos.PersonaDTO;
import com.InvTierradentro.invtierradentro.aplication.models.Dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*/")
public class UserController {

    @Autowired
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value="/buscar")
    public UserDTO FethUser(@RequestParam String username){
        UserDTO userDao= usuarioService.findByUsername(username);
        return userDao;
    }

    @PostMapping(value="/guardar")
    public void SaveUser(@RequestBody PersonaDTO personasDTO){
        usuarioService.SaveUser(personasDTO);
    }

    @GetMapping(value="/buscartodos")
    List<PersonaDTO> findALL(){
        List<PersonaDTO> personas=usuarioService.findall();
        return  personas;
    }




}
