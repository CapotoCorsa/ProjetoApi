package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.enums.StatusAgendamento;
import br.com.serratec.projetoapi.exception.AgendamentoException;
import br.com.serratec.projetoapi.model.Agendamento;
import br.com.serratec.projetoapi.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public Agendamento agendar(Agendamento agendamento) {
        Boolean horarioOcupado= repository
                                .existsByDataAndHora(
                                    agendamento.getData(), 
                                    agendamento.getHora()
                                );
        if(horarioOcupado) {
            throw new AgendamentoException("Horário indisponível.");
        }
        
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        return repository.save(agendamento);
    }

    public Agendamento cancelar(Agendamento agendamento) {
        if(agendamento.getStatus().name()== "CANCELADO") {
            throw new AgendamentoException("Agendamento já cancelado.");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return repository.save(agendamento);
    }

    public Agendamento concluir(Agendamento agendamento) {
        if(agendamento.getStatus().name()== "CANCELADO") {
            throw new AgendamentoException("Agendamento já cancelado.");
        }

        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        return repository.save(agendamento);
    }

    public List<Agendamento> listarPorData(LocalDate data) {
        return repository.findByData(data);
    }

    public Boolean buscar(Long id) {
        if(repository.existsById(id)) {
            return true;
        }
        throw new AgendamentoException("Agendamento inválido ou não encontrado.");
    }
}
