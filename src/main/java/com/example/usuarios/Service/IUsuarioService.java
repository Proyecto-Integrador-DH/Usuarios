package com.example.usuarios.Service;

import com.example.usuarios.Model.DTOs.UsuarioDTO;
import com.example.usuarios.Model.Usuario;

public interface IUsuarioService {
    Usuario postUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO getUsuario(String email);
    UsuarioDTO getUsuarioById(Integer id);
}
