package com.example.usuarios.Repository;

import com.example.usuarios.Model.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermisosRepository extends JpaRepository<Permisos, Integer> {
}
