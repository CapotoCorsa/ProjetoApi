package br.com.serratec.projetoapi.model;

import br.com.serratec.projetoapi.enums.DescricaoServico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha o valor.")
    private Double valor;

    @NotNull(message= "Preencha a descrição.")
    @Enumerated(EnumType.STRING)
    private DescricaoServico descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public DescricaoServico getDescricao() {
        return descricao;
    }

    public void setDescricao(DescricaoServico descricao) {
        this.descricao = descricao;
    }
    
}
