package br.com.serratec.projetoapi.model;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.serratec.projetoapi.enums.StatusAgendamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha a data.")
    private LocalDate data;

    @NotNull(message= "Preencha a hora.")
    private LocalTime hora;
    
    @NotNull(message= "Preencha o status.")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;
    
    @NotNull(message= "Preencha o ID do cliente.")
    @ManyToOne
    private Cliente cliente;
    
    @NotNull(message= "Preencha o ID do veículo.")
    @ManyToOne
    private Veiculo veiculo;
    
    @NotNull(message= "Preencha o ID do serviço.")
    @ManyToOne
    private Servico servico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
}
