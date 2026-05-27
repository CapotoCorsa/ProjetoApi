package br.com.serratec.projetoapi.dto;

public record VeiculoRequestDTO(String placa, String marca, String modelo, Integer ano, String cor, Long idCliente) {

}
