package com.luisangulo.demo.controller;



import com.luisangulo.demo.dto.JwtDTO;
import com.luisangulo.demo.dto.LoginUsuario;
import com.luisangulo.demo.dto.Mensaje;
import com.luisangulo.demo.dto.NuevoUsuario;
import com.luisangulo.demo.entity.Rol;
import com.luisangulo.demo.entity.Usuario;
import com.luisangulo.demo.enums.RolNombre;
import com.luisangulo.demo.security.jwt.JwtProvider;
import com.luisangulo.demo.service.RolService;
import com.luisangulo.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;



    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);

        ResponseEntity<Mensaje> existingUserResponse = usuarioService.checkExistingUser(nuevoUsuario);
        if (existingUserResponse != null) {
            return existingUserResponse;
        }

        Usuario usuario = usuarioService.createNewUsuario(nuevoUsuario);
        Set<Rol> roles = usuarioService.mapRoles(nuevoUsuario.getRoles());
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }
    @PostMapping("/agregarRol")
    public ResponseEntity<String> agregar(@RequestBody Rol rol){
         rolService.agregarRol(rol);
          return new ResponseEntity(new Mensaje("rol guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}
