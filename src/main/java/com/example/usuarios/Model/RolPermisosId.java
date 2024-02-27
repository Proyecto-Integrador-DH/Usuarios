package com.example.usuarios.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RolPermisosId implements Serializable {
    @Column(name = "rol_id")
    private Integer rolId;
    @Column(name = "permisos_id")
    private Integer permisosId;
}
