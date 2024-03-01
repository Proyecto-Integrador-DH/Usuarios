package com.example.usuarios.Model.DTOs;

import java.util.List;

public record UsuarioDTO(Integer id, String nombre, String apellido, String email, String pass, List<RolDTOUsuario> roles) {
    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public List<RolDTOUsuario> getRoles() {
        return roles;
    }
}
