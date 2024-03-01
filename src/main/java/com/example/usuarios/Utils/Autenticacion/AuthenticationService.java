package com.example.usuarios.Utils.Autenticacion;
import com.example.usuarios.Model.Rol;
import com.example.usuarios.Model.Usuario;
import com.example.usuarios.Service.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private IUsuarioService usuarioService;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String authenticate(String email, String pass) {
        Usuario usuario = usuarioService.getUsuario(email);
        System.out.println(usuario.getRoles());
        if (usuario.getEmail().equals(email) && usuario.getPass().equals(pass)) {
            return generateToken(usuario.getEmail(), usuario.getRoles());
        } else {
            return null;
        }
    }

    private String generateToken(String email, List<Rol> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 días de validez
                .signWith(key) // Clave secreta para firmar el token
                .compact();
    }
}
