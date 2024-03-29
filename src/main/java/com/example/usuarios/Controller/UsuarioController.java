package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.RolDTOUsuario;
import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Service.IRolService;
import com.example.usuarios.Service.IUsuarioService;
import com.example.usuarios.Utils.Autenticacion.AuthenticationService;
//import com.example.usuarios.Utils.Autenticacion.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private AuthenticationService authenticationService;
    /*@Autowired
    private EmailService emailService;*/

    Boolean tieneRolAdmin = false;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioValido = usuarioService.getUsuario(usuarioDTO.getEmail());
            if (usuarioValido != null) {
                String email = usuarioDTO.getEmail();
                String pass = usuarioDTO.getPass();
                String token = authenticationService.authenticate(email, pass);
                if (token != null) {
                    return ResponseEntity.ok(token);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el email especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoUsuario(@RequestBody UsuarioDTO usuarioDTO){
        List<RolDTOUsuario> roles = usuarioDTO.getRoles();
        System.out.println("usuario" + usuarioDTO);
        try {
            if(roles == null || roles.isEmpty()){
                roles = new ArrayList<>();
                roles.add(new RolDTOUsuario(2,"Usuario"));
                usuarioDTO = new UsuarioDTO(usuarioDTO.id(), usuarioDTO.nombre(), usuarioDTO.apellido(), usuarioDTO.email(), usuarioDTO.pass(), roles);
            }
            usuarioService.postUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Ya existe un usuario con ese email.", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable String email){
        try {
            UsuarioDTO usuarioDTO = usuarioService.getUsuario(email);
            if (usuarioDTO != null) {
                return ResponseEntity.ok(usuarioDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el email especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUsuario(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (tieneRolAdmin) {
                UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id);
                if (usuarioDTO != null) {
                    return ResponseEntity.ok(usuarioDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el ID especificado.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene permiso para ver este usuario.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> getTodosUsuarios(@RequestHeader("Authorization") String token) {
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (tieneRolAdmin) {
                List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
                if (usuarios.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios.");
                } else {
                    return ResponseEntity.ok(usuarios);
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene permiso para ver este listado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @PostMapping("/asignarRol")
    public ResponseEntity<?> asignarRol(@RequestHeader("Authorization") String token, @RequestBody UsuarioDTO usuarioDTO){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (!tieneRolAdmin) {
                return ResponseEntity.status(401).body("No tiene permisos para realizar esta acción.");
            }
            UsuarioDTO usuario = usuarioService.getUsuarioById(usuarioDTO.id());
            List<RolDTOUsuario> roles = usuario.getRoles();

            // Verifica si el usuario ya tiene el rol asignado
            if (roles.stream().anyMatch(rol -> rol.getId() == 1)) {
                return ResponseEntity.status(400).body("El usuario ya tiene el rol de Administrador asignado.");
            }

            // Agrega el nuevo rol al usuario
            roles.add(new RolDTOUsuario(1, "Administrador"));
            usuarioService.addRol(usuario);

            return ResponseEntity.status(201).body("Rol asignado con éxito.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PostMapping("/quitarRol")
    public ResponseEntity<?> quitarRol(@RequestHeader("Authorization") String token, @RequestBody UsuarioDTO usuarioDTO){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (!tieneRolAdmin) {
                return ResponseEntity.status(401).body("No tiene permisos para realizar esta acción.");
            }

            UsuarioDTO usuario = usuarioService.getUsuarioById(usuarioDTO.id());
            List<RolDTOUsuario> roles = usuario.getRoles();

            // Verifica si el usuario tiene el rol de Administrador
            if (roles.stream().noneMatch(rol -> rol.getId() == 1)) {
                return ResponseEntity.status(400).body("El usuario no tiene el rol de Administrador asignado.");
            }

            // Elimina el rol de Administrador del usuario
            roles.removeIf(rol -> rol.getId() == 1);
            usuarioService.deleteRol(usuario);

            return ResponseEntity.status(201).body("Rol eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}