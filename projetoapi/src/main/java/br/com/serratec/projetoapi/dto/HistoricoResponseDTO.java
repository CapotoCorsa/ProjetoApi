package br.com.serratec.projetoapi.dto;

public record HistoricoResponseDTO(
    Long id,
    String dataManutencao,
    String descricao,
    Double valorCobrado,
    Long veiculoId
) {}