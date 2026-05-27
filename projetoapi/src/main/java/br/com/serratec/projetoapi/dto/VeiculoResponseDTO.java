package br.com.serratec.projetoapi.dto;

import br.com.serratec.projetoapi.model.Cliente;

public record VeiculoResponseDTO(Long idVeiculo, String placaVeiculo, String modeloVeiculo, Cliente proprietario) {

}
