package br.com.serratec.projetoapi.dto;

public record OrdemServicoResponseDTO(Long id, Long idVeiculo, String statusOrdem, Double totalGeral) {

}
