package com.example.usuarios.Controller;

import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Usuario;
import com.example.usuarios.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoUsuario(@RequestBody UsuarioDTO usuarioDTO){
        try {
            usuarioService.postUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Ya existe un usuario con ese email.", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable String email){
        try {
            Usuario usuario = usuarioService.getUsuario(email);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún usuario con el email especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }
}