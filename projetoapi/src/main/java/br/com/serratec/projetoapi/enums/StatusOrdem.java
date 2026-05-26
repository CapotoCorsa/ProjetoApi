package br.com.serratec.projetoapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.projetoapi.exception.EnumException;

public enum StatusOrdem {
    ABERTA, EM_ANDAMENTO, FINALIZADA, CANCELADA;

    @JsonCreator
    public static DescricaoServico verificaEnum(String nome) {
        for(DescricaoServico servico: DescricaoServico.values()) {
            if(servico.name().equals(nome))
                return servico;
        }
        throw new EnumException("Status inválido.");
    }
}
