package com.example.usuarios.Service.Impl;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.Permisos;
import com.example.usuarios.Model.Rol;
import com.example.usuarios.Repository.IPermisosRepository;
import com.example.usuarios.Repository.IRolRepository;
import com.example.usuarios.Service.IRolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {

    @Autowired
    private IRolRepository rolRepository;
    @Autowired
    private IPermisosRepository permisosRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public Rol postRol(RolDTO rolDTO) {
        Rol rol = mapper.convertValue(rolDTO, Rol.class);
        Rol rolNuevo = rolRepository.save(rol);
        return rolNuevo;
    }

    @Override
    public Rol getRol(Integer id) {
        return rolRepository.findById(id).get();
    }

    @Override
    public void addPermiso(Integer rolId, Integer permisoId) {
        Rol rol = rolRepository.findById(rolId).orElseThrow(() -> new NoSuchElementException("Rol no encontrado"));
        Permisos permiso = permisosRepository.findById(permisoId).orElseThrow(() -> new NoSuchElementException("Permiso no encontrado"));

        rol.getPermisos().add(permiso);
        rolRepository.save(rol);
    }
}
