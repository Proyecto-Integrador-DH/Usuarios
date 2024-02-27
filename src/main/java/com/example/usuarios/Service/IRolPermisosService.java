package com.example.usuarios.Service;

public interface IRolPermisosService {
    public void asignarPermisos(Integer rolId, Integer permisoId);
    public void quitarPermisos(Integer rolId, Integer permisoId);
}
