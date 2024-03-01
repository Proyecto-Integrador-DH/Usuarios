package com.example.usuarios.Service.Impl;

import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Usuario;
import com.example.usuarios.Repository.IUsuarioRepository;
import com.example.usuarios.Service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    ObjectMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Usuario postUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        Usuario usuarioNuevo = usuarioRepository.save(usuario);
        usuarioNuevo = usuarioRepository.findById(usuarioNuevo.getId()).orElse(null);
        return usuarioNuevo;
    }

    @Override
    public Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
