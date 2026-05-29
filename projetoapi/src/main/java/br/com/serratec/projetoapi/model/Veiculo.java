package br.com.serratec.projetoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message= "Preencha a placa.")
    private String placa;
    
    @NotBlank(message= "Preencha a marca.")
    private String marca;

    @NotBlank(message= "Preencha o modelo.")
    private String modelo;
    
    @NotNull(message= "Preencha o ano.")
    private Integer ano;
    
    @NotBlank(message= "Preencha o modelo.")
    private String cor;

    @NotNull(message= "Preencha o ID do cliente.")
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Cliente getCliente() {
    return cliente;
    }

    public void setCliente(Cliente cliente) {
    this.cliente = cliente;
    }


    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    public void setId(Long id) {
    this.id = id;
}
}
