package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.AgendamentoResponseDTO;
import br.com.serratec.projetoapi.dto.VeiculoResponseDTO;
import br.com.serratec.projetoapi.dto.AgendamentoRequestDTO;
import br.com.serratec.projetoapi.enums.StatusAgendamento;
import br.com.serratec.projetoapi.exception.AgendamentoException;
import br.com.serratec.projetoapi.exception.ServicoException;
import br.com.serratec.projetoapi.exception.VeiculoException;
import br.com.serratec.projetoapi.model.Agendamento;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.model.Servico;
import br.com.serratec.projetoapi.repository.AgendamentoRepository;
import br.com.serratec.projetoapi.repository.ServicoRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO dto) {
        Boolean horarioOcupado= repository
                                .existsByDataAndHora(
                                    dto.data(), 
                                    dto.hora()
                                );
        if(horarioOcupado) {
            throw new AgendamentoException("Horário indisponível.");
        }

        Veiculo veiculo= veiculoRepository
                         .findById(dto.veiculoId())
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));

        Servico servico= servicoRepository
                         .findById(dto.servicoId())
                         .orElseThrow(()-> new ServicoException("Serviço não encontrado."));

        Agendamento agendamento= new Agendamento();
        agendamento.setData(dto.data());
        agendamento.setHora(dto.hora());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        agendamento.setVeiculo(veiculo);
        agendamento.setServico(servico);
        
        repository.save(agendamento);
        return new AgendamentoResponseDTO(agendamento.getId(), agendamento.getData(), agendamento.getHora(), agendamento.getStatus().name(), veiculo.getId(), servico.getId()); 
    }

    public AgendamentoResponseDTO cancelar(Long id) {
        Agendamento agendamento= repository
                                 .findById(id)
                                 .orElseThrow(()-> new AgendamentoException("Agendamento não encontrado."));
        if(agendamento.getStatus().name()== "CANCELADO") {
            throw new AgendamentoException("Agendamento já cancelado.");
        } else if(agendamento.getStatus().name()== "CONCLUIDO") {
            throw new AgendamentoException("Agendamento já concluído.");
        }

        agendamento.setId(id);
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        
        repository.save(agendamento);
        return new AgendamentoResponseDTO(agendamento.getId(), agendamento.getData(), agendamento.getHora(), agendamento.getStatus().name(), agendamento.getVeiculo().getId(), agendamento.getServico().getId());
    }

    public AgendamentoResponseDTO concluir(Long id) {
        Agendamento agendamento= repository
                                 .findById(id)
                                 .orElseThrow(()-> new AgendamentoException("Agendamento não encontrado."));
        if(agendamento.getStatus().name()== "CANCELADO") {
            throw new AgendamentoException("Agendamento já cancelado.");
        } else if(agendamento.getStatus().name()== "CONCLUIDO") {
            throw new AgendamentoException("Agendamento já concluído.");
        }

        agendamento.setId(id);
        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        
        repository.save(agendamento);
        return new AgendamentoResponseDTO(agendamento.getId(), agendamento.getData(), agendamento.getHora(), agendamento.getStatus().name(), agendamento.getVeiculo().getId(), agendamento.getServico().getId());
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
