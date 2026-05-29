package br.com.serratec.projetoapi.dto;

import java.util.List;

public record VeiculoResponseDTO(Long idVeiculo, String placaVeiculo, String modeloVeiculo, ClienteResponseDTO proprietario, List<String> url) {

}