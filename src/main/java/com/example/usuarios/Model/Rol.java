package com.example.usuarios.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
            name = "rol_permisos",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private Set<Permisos> permisos = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

    public <E> List<E> getUsuario() {
        return (List<E>) usuarios;
    }

    public Integer getId() {
        return id;
    }

    public void addPermiso(Permisos permiso) {
        this.permisos.add(permiso);
        permiso.getRoles().add(this);
    }

    public Set<Permisos> getPermisos() {
        return permisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setNombre(String administrador) {
        this.nombre = administrador;
    }
}
