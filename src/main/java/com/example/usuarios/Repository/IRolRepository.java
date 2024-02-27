package com.example.usuarios.Repository;

import com.example.usuarios.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}
