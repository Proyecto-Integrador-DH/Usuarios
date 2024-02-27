package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.PermisosDTO;
import com.example.usuarios.Service.IPermisosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permisos")
public class PermisosController {
    @Autowired
    IPermisosService permisosService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoPermiso(@RequestBody PermisosDTO  permisosDTO){
        try {
            permisosService.postPermisos(permisosDTO);
            return ResponseEntity.status(201).body("Permiso creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}
