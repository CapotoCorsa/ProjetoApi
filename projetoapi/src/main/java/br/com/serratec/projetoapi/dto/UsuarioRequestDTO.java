package br.com.serratec.projetoapi.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.projetoapi.model.UsuarioPerfil;

public class UsuarioRequestDTO {
    private String nome;
    private String senha;
    private String email;

    private Set<UsuarioPerfil> usuarioPerfis= new HashSet<>();

    public UsuarioRequestDTO(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
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

}
