package br.com.serratec.projetoapi.dto;

import br.com.serratec.projetoapi.enums.StatusOrdem;

public class OrdemServicoRequestDTO {
    private Long idVeiculo;
    private StatusOrdem statusOrdem;
    
    public OrdemServicoRequestDTO(Long idVeiculo, StatusOrdem statusOrdem) {
        this.idVeiculo = idVeiculo;
        this.statusOrdem = statusOrdem;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }
    
}
