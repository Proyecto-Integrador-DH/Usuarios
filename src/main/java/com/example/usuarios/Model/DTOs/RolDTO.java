package com.example.usuarios.Model.DTOs;

import com.example.usuarios.Model.Permisos;
import java.util.List;
import java.util.Set;

public record RolDTO(Integer id, String nombre, List<Permisos> permisos) {

    public List<Permisos> getPermisos() {
        return (List<Permisos>)permisos;
    }

    public Integer getRolId() {
        return id;
    }
}
