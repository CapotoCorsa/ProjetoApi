package br.com.serratec.projetoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha o ID da ordem de serviço.")
    @ManyToOne
    @JoinColumn(name= "id_ordem")
    private OrdemServico ordem;
    
    @NotNull(message= "Preencha o ID do serviço.")
    @ManyToOne
    @JoinColumn(name= "id_servico")
    private Servico servico;
    
    private Double subtotal;
    private Double desconto;
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdem() {
        return ordem;
    }
    
    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }
    
    public Servico getServico() {
        return servico;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public Double getSubtotal() {
        this.subtotal= (servico.getValor()* (quantidade!= null? quantidade: 1))* (1- ((desconto!= null? desconto: 0.0)/ 100));
        return subtotal;
    }
    
    public Double getDesconto() {
        return desconto;
    }
    
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
}
