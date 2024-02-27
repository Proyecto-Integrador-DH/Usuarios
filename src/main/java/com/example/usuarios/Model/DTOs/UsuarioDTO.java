package com.example.usuarios.Model.DTOs;

import com.example.usuarios.Model.Rol;

public record UsuarioDTO(String nombre, String apellido, String email, String pass, Rol rol) {
}
