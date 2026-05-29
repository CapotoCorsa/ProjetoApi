package br.com.serratec.projetoapi.dto;

public record CheckoutRequestDTO(Long ordemId, Long servicoId, Integer quantidade, Double desconto) {

}