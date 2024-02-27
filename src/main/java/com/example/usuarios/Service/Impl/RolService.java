package com.example.usuarios.Service.Impl;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.Rol;
import com.example.usuarios.Repository.IRolRepository;
import com.example.usuarios.Service.IRolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {

    @Autowired
    private IRolRepository rolRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public Rol postRol(RolDTO rolDTO) {
        Rol rol = mapper.convertValue(rolDTO, Rol.class);
        Rol rolNuevo = rolRepository.save(rol);
        return rolNuevo;
    }
}
