package com.example.usuarios.Utils.Autenticacion;
import com.example.usuarios.Model.DTOs.RolDTOUsuario;
import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Rol;
import com.example.usuarios.Model.Usuario;
import com.example.usuarios.Service.IUsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        List<Map<String, Object>> rolesMap = (List<Map<String, Object>>) claims.get("roles");
        List<RolDTOUsuario> roles = rolesMap.stream()
                .map(map -> new RolDTOUsuario((Integer) map.get("id"), (String) map.get("nombre")))
                .collect(Collectors.toList());
        boolean tieneRolAdmin = false;
        for (RolDTOUsuario rol : roles) {
            if (rol.getNombre().equals("Administrador")) {
                tieneRolAdmin = true;
                break;
            }
        }
        return tieneRolAdmin;
    }
}
