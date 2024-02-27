package com.example.usuarios.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Permisos> permisos;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}
