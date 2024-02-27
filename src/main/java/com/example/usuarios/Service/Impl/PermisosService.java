package com.example.usuarios.Service.Impl;

import com.example.usuarios.Model.DTOs.PermisosDTO;
import com.example.usuarios.Model.Permisos;
import com.example.usuarios.Repository.IPermisosRepository;
import com.example.usuarios.Service.IPermisosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisosService implements IPermisosService {
    @Autowired
    private IPermisosRepository permisosRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public Permisos postPermisos(PermisosDTO permisosDTO) {
        Permisos permisos = mapper.convertValue(permisosDTO, Permisos.class);
        Permisos permisosNuevo = permisosRepository.save(permisos);
        return permisosNuevo;
    }
}