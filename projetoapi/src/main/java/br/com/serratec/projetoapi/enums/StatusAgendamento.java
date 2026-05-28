package br.com.serratec.projetoapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.projetoapi.exception.EnumException;

public enum StatusAgendamento {
    AGENDADO,
    CONCLUIDO,
    CANCELADO;

    @JsonCreator
    public static StatusAgendamento verificaEnum(String nome) {
        for(StatusAgendamento status: StatusAgendamento.values()) {
            if(status.name().equals(nome))
                return status;
        }
        throw new EnumException("Status inválido.");
    }
}
