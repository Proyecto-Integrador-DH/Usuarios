package com.example.usuarios.Service;

import com.example.usuarios.Model.DTOs.PermisosDTO;
import com.example.usuarios.Model.Permisos;

public interface IPermisosService {
    Permisos postPermisos(PermisosDTO permisosDTO);
}
