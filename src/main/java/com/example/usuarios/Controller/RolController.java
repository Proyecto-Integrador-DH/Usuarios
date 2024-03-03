package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.Permisos;
import com.example.usuarios.Service.IRolService;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.usuarios.Utils.Autenticacion.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    IRolService rolService;
    @Autowired
    private AuthenticationService authenticationService;
    Boolean tieneRolAdmin = false;
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoRol(@RequestHeader("Authorization") String token, @RequestBody RolDTO rolDTO){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (!tieneRolAdmin) {
                return ResponseEntity.status(401).body("No tiene permisos para realizar esta acción.");
            }
            rolService.postRol(rolDTO);
            return ResponseEntity.status(201).body("Rol creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
    @PostMapping("/asignarPermiso")
    public ResponseEntity<?> asignarPermiso(@RequestHeader("Authorization") String token, @RequestBody RolDTO rolDTO){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (!tieneRolAdmin) {
                return ResponseEntity.status(401).body("No tiene permisos para realizar esta acción.");
            }
            rolService.addPermiso(rolDTO.getRolId(), rolDTO.getPermisos().get(0).getId());
            return ResponseEntity.status(201).body("Permiso asignado con éxito.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Hubo un error al procesar la solicitud.");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}
