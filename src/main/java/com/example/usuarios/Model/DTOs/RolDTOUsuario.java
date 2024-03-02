package com.example.usuarios.Model.DTOs;

public record RolDTOUsuario(Integer id, String nombre) {
    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }
}
