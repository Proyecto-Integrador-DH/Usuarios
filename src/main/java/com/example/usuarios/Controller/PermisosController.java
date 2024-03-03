package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.PermisosDTO;
import com.example.usuarios.Service.IPermisosService;
import com.example.usuarios.Utils.Autenticacion.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permisos")
public class PermisosController {
    @Autowired
    IPermisosService permisosService;
    @Autowired
    private AuthenticationService authenticationService;

    Boolean tieneRolAdmin = false;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoPermiso(@RequestHeader("Authorization") String token, @RequestBody PermisosDTO  permisosDTO){
        try {
            tieneRolAdmin = authenticationService.getRolesFromToken(token);
            if (!tieneRolAdmin) {
                return ResponseEntity.status(401).body("No tiene permisos para realizar esta acción.");
            }
            permisosService.postPermisos(permisosDTO);
            return ResponseEntity.status(201).body("Permiso creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}
