package br.com.serratec.projetoapi.model;

import jakarta.persistence.*;




@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;

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
