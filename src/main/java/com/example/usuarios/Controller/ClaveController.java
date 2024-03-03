package com.example.usuarios.Controller;

import com.example.usuarios.Utils.Autenticacion.ClaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Base64;

@RestController
@RequestMapping("/clave")
public class ClaveController {
    @Autowired
    private ClaveService claveService;

    @GetMapping()
    public ResponseEntity<String> obtenerClavePublica() {
        Key publicKey = claveService.getPublicKey();
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return ResponseEntity.ok(publicKeyBase64);
    }
}
