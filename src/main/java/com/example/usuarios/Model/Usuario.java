package com.example.usuarios.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NotNull()
    @NotEmpty()
    @Size(min = 3, max = 50)
    private String nombre;
    @NotNull()
    @NotEmpty()
    @Size(min = 3, max = 50)
    private String apellido;
    @NotNull()
    @NotEmpty()
    @Email
    private String email;
    @NotNull()
    @NotEmpty()
    @Size(min = 8, max = 50)
    private String pass;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;
}
