package br.com.serratec.projetoapi.dto;

import java.time.LocalDate;

public record AvaliacaoResponseDTO(
    Long id,
    Integer nota,
    String comentario,
    LocalDate data,
    Long ordemServicoId
) {}