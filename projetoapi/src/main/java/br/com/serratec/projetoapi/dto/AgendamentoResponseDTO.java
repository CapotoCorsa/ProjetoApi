package br.com.serratec.projetoapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponseDTO(
    Long id,
    LocalDate data,
    LocalTime hora,
    String status,
    Long veiculoId,
    Long servicoId
) {}