package br.com.serratec.projetoapi.dto;

public record VeiculoResponseDTO(Long idVeiculo, String placaVeiculo, String modeloVeiculo, ClienteResponseDTO proprietario, String url) {

}