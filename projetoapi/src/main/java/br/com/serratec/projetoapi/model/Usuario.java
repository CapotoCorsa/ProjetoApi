package br.com.serratec.projetoapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message= "Preencha o nome.")
    private String nome;

    @NotBlank(message= "Preencha o senha.")
    private String senha;

    @NotBlank(message= "Preencha o e-mail.")
    @Email(message= "Insira um e-mail válido.")
    private String email;

    @OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER)
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    @Override
    public String toString() {
        return " Nome:" + nome +"\n Email:" + email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (UsuarioPerfil up : usuarioPerfis) {
                authorities.add(new SimpleGrantedAuthority(up.getPerfil().getNome()));
            }
            return authorities;

    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    
}