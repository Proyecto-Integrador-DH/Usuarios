package com.example.usuarios.Model.DTOs;
import com.example.usuarios.Model.Rol;
import lombok.Getter;

import java.util.List;

public record UsuarioDTO(String nombre, String apellido, String email, String pass, List<Rol> roles) {
    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
