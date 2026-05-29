package br.com.serratec.projetoapi.dto;

public record CheckoutResponseDTO(Long id, Long ordemId, Long servicoId, Integer quantidade, Double desconto, Double subtotal) {

}