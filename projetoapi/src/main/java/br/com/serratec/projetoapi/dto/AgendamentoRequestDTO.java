package br.com.serratec.projetoapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.serratec.projetoapi.enums.StatusAgendamento;

public record AgendamentoRequestDTO(
    LocalDate data,
    LocalTime hora,
    StatusAgendamento status,
    Long veiculoId,
    Long servicoId
) {}