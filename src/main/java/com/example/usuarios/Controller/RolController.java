package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    IRolService rolService;
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoRol(@RequestBody RolDTO rolDTO){
        try {
            rolService.postRol(rolDTO);
            return ResponseEntity.status(201).body("Rol creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}
