package com.example.usuarios.Service;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.Rol;

public interface IRolService {
    Rol postRol(RolDTO rolDTO);
    Rol getRol(Integer id);

    void addPermiso(Integer rolId, Integer permisoId);
    void removePermiso(Integer rolId, Integer permisoId);
}
