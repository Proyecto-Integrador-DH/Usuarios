package com.example.usuarios.Utils.Autenticacion;
import com.example.usuarios.Model.DTOs.RolDTOUsuario;
import com.example.usuarios.Model.DTOs.UsuarioDTO;
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
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private IUsuarioService usuarioService;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String authenticate(String email, String pass) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(email);
        if (usuarioDTO.getEmail().equals(email) && usuarioDTO.getPass().equals(pass)) {
            return generateToken(usuarioDTO.getEmail(), (List<RolDTOUsuario>)usuarioDTO.getRoles());
        } else {
            return null;
        }
    }

    private String generateToken(String email, List<RolDTOUsuario> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 días de validez
                .signWith(key) // Clave secreta para firmar el token
                .compact();
    }
}
