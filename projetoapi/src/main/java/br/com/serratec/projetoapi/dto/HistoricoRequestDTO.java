package br.com.serratec.projetoapi.dto;

public record HistoricoRequestDTO(
    String dataManutencao,
    String descricao,
    Double valorCobrado,
    Long veiculoId
) {}