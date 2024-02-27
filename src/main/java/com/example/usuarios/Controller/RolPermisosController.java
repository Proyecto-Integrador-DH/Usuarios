package com.example.usuarios.Controller;

import com.example.usuarios.Service.IRolPermisosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rolpermisos")
public class RolPermisosController {
    @Autowired
    private IRolPermisosService rolPermisosService;

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarPermisos(@RequestParam("rolId") Integer rolId, @RequestParam("permisoId") Integer permisoId){
        try {
            rolPermisosService.asignarPermisos(rolId, permisoId);
            return ResponseEntity.ok("Permisos asignados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PostMapping("/quitar")
    public ResponseEntity<?> quitarPermisos(@RequestParam Integer rolId, @RequestParam Integer permisoId){
        try {
            rolPermisosService.quitarPermisos(rolId, permisoId);
            return ResponseEntity.ok("Permisos quitados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

}
