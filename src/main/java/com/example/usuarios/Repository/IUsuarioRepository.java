package com.example.usuarios.Repository;

import com.example.usuarios.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}
