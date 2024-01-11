package com.luisangulo.demo.service;

import com.luisangulo.demo.dto.Mensaje;
import com.luisangulo.demo.dto.NuevoUsuario;
import com.luisangulo.demo.entity.Rol;
import com.luisangulo.demo.entity.Usuario;
import com.luisangulo.demo.enums.RolNombre;
import com.luisangulo.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;
    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario createNewUsuario(NuevoUsuario nuevoUsuario) {
        return new Usuario(
                nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getDni(),
                nuevoUsuario.getFoto(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
    }

    public Set<Rol> mapRoles(Set<String> rolesStr) {
        return rolesStr.stream()
                .map(rol -> {
                    switch (rol) {
                        case "admin":
                            return rolService.getByRolNombre(RolNombre.ROLE_ADMIN).orElse(null);
                        default:
                            return rolService.getByRolNombre(RolNombre.ROLE_USER).orElse(null);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public ResponseEntity<Mensaje> checkExistingUser(NuevoUsuario nuevoUsuario) {
        if (existePorNombre(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        if (existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);

        return null;
    }
}
