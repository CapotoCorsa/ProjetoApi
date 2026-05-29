package br.com.serratec.projetoapi.dto;


public record AvaliacaoRequestDTO(
    Integer nota,
    String comentario,
    Long ordemServicoId
) {}