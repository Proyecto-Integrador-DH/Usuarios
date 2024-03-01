package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.Permisos;
import com.example.usuarios.Service.IRolService;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/asignarPermiso")
    public ResponseEntity<?> asignarPermiso(@RequestBody RolDTO rolDTO){
        try {
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
