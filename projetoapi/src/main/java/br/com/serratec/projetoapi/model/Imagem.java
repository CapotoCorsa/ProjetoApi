//essa é a parte individual do Douglas Mathias Barboza =D
//decidi fazer uma das sugestões do Roni na atividade que é a de upload de imagens 👍

package br.com.serratec.projetoapi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private byte[] dados;
    private String tipo;
    private String nome;

    public Imagem(){

    }

    public Imagem(Long id, byte[] dados, String tipo, String nome) {
        this.id = id;
        this.dados = dados;
        this.tipo = tipo;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

}
