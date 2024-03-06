package com.example.usuarios.Service.Impl;

import com.example.usuarios.Model.DTOs.RolDTO;
import com.example.usuarios.Model.DTOs.RolDTOUsuario;
import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Rol;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public UsuarioDTO getUsuario(String email) {
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioRepository.findByEmail(email));
        UsuarioDTO usuarioDTO = null;
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuarioDTO = convertToDto(usuario);
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO getUsuarioById(Integer id) {
        Optional<Usuario> opcionalUsuario = usuarioRepository.findById(id);
        UsuarioDTO usuarioDTO = null;
        if (opcionalUsuario.isPresent()) {
            Usuario usuario = opcionalUsuario.get();
            usuarioDTO = convertToDto(usuario);
        }
        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDto = usuarios.stream()
                .map(usuario -> convertToDto(usuario))
                .collect(Collectors.toList());
        return usuariosDto;
    }

    @Override
    public void addRol(UsuarioDTO usuarioDTO) {

        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        Usuario usuarioRol = usuarioRepository.save(usuario);
        usuarioRol = usuarioRepository.findById(usuarioRol.getId()).orElse(null);
        usuarioRepository.save(usuarioRol);
    }

    @Override
    public void deleteRol(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        Usuario usuarioRol = usuarioRepository.save(usuario);
        usuarioRol = usuarioRepository.findById(usuarioRol.getId()).orElse(null);
        usuarioRepository.save(usuarioRol);
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        List<RolDTOUsuario> rolesDto = usuario.getRoles().stream()
                .map(rol -> new RolDTOUsuario(rol.getId(), rol.getNombre()))
                .collect(Collectors.toList());
        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getPass(),
                rolesDto
        );
        return dto;
    }
}
