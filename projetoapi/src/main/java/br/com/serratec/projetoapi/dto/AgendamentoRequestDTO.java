package br.com.serratec.projetoapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequestDTO(
    LocalDate data,
    LocalTime hora,
    Long veiculoId,
    Long servicoId
) {}