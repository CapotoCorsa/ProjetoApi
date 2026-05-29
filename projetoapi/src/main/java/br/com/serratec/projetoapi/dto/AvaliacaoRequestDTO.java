package br.com.serratec.projetoapi.dto;

import java.time.LocalDate;

public record AvaliacaoRequestDTO(
    Integer nota,
    String comentario,
    LocalDate data,
    Long clienteId
) {}