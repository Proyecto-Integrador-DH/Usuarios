package com.example.usuarios.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private String permisos;

    @ManyToMany(mappedBy = "permisos")
    private Set<Rol> roles;

    public <E> List<E> getRoles() {
        return (List<E>) roles;
    }


    public void addRol(Rol rol) {
        this.roles.add(rol);
        rol.getPermisos().add(this);
    }

    public String getNombre() {
        return permisos;
    }

    public Integer getId() {
        return id;
    }
}
