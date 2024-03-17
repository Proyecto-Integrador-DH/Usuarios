package com.example.usuarios.Service;

import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Usuario;

import javax.mail.MessagingException;
import java.util.List;

public interface IUsuarioService {
    Usuario postUsuario(UsuarioDTO usuarioDTO) throws MessagingException, jakarta.mail.MessagingException;
    UsuarioDTO getUsuario(String email);
    UsuarioDTO getUsuarioById(Integer id);
    List<UsuarioDTO> getAllUsuarios();

    void addRol(UsuarioDTO usuarioDTO);
    void deleteRol(UsuarioDTO usuarioDTO);
}
