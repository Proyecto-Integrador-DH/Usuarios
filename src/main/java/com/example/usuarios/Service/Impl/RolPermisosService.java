package com.example.usuarios.Service.Impl;

import com.example.usuarios.Repository.IRolPermisosRepository;
import com.example.usuarios.Service.IRolPermisosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolPermisosService implements IRolPermisosService {
    @Autowired
    private IRolPermisosRepository rolPermisosRepository;
    @Override
    public void asignarPermisos(Integer rolId, Integer permisoId) {
        System.out.println("Asignando permisos al rol con ID: " + rolId);
    }

    @Override
    public void quitarPermisos(Integer rolId, Integer permisoId) {
        System.out.println("Quitando permisos al rol con ID: " + rolId);
    }

}
