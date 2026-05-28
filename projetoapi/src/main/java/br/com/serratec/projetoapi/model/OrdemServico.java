package br.com.serratec.projetoapi.model;

import java.util.List;

import br.com.serratec.projetoapi.enums.StatusOrdem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha o ID do veículo.")
    @ManyToOne
    @JoinColumn(name= "id_veiculo")
    private Veiculo veiculo;

    @NotNull(message= "Preencha o status.")
    @Enumerated(EnumType.STRING)
    private StatusOrdem status;

    @OneToMany(mappedBy = "ordem")
    private List<Checkout> checkouts;

    private Double totalGeral;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public StatusOrdem getStatus() {
        return status;
    }

    public void setStatus(StatusOrdem status) {
        this.status = status;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public Double getTotalGeral() {
        for (Checkout checkout : checkouts) {
            totalGeral+= checkout.getSubtotal();
        }
        return totalGeral;
    }

    public void setTotalGeral(Double totalGeral) {
        this.totalGeral = totalGeral;
    }

}
