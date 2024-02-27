package com.example.usuarios.Repository;

import com.example.usuarios.Model.RolPermisos;
import com.example.usuarios.Model.RolPermisosId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolPermisosRepository extends JpaRepository<RolPermisos, RolPermisosId> {
}
