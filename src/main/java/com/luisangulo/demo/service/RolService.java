package com.luisangulo.demo.service;


import com.luisangulo.demo.entity.Rol;
import com.luisangulo.demo.enums.RolNombre;
import com.luisangulo.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void agregarRol(Rol roles){
        rolRepository.save(roles);

    }
}
