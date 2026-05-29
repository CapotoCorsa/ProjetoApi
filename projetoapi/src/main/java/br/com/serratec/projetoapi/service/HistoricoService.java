package br.com.serratec.projetoapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.HistoricoRequestDTO;
import br.com.serratec.projetoapi.dto.HistoricoResponseDTO;
import br.com.serratec.projetoapi.exception.HistoricoException;
import br.com.serratec.projetoapi.exception.VeiculoException;
import br.com.serratec.projetoapi.model.Historico;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.HistoricoRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public HistoricoResponseDTO salvar(HistoricoRequestDTO dto) {
        Veiculo veiculo= veiculoRepository
                         .findById(dto.veiculoId())
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));
        
        Historico historico= new Historico();
        historico.setDataManutencao(dto.dataManutencao());
        historico.setDescricao(dto.descricao());
        historico.setValorCobrado(dto.valorCobrado());
        historico.setVeiculo(veiculo);
        
        repository.save(historico);
        return new HistoricoResponseDTO(historico.getId(), dto.dataManutencao(), dto.descricao(), dto.valorCobrado(), veiculo.getId());
    }

    //esses dois métodos estão colados para indicar que são "a mesma coisa"
    public Page<HistoricoResponseDTO> buscarPorVeiculo(Long veiculoId, Pageable pageable) {
        return repository.findByVeiculoId(veiculoId, pageable)
                .map(this::toDTO);
    }
    private HistoricoResponseDTO toDTO(Historico historico) {
        return new HistoricoResponseDTO(
                historico.getId(),
                historico.getDataManutencao(),
                historico.getDescricao(),
                historico.getValorCobrado(),
                historico.getVeiculo().getId()
        );
    }

    public Boolean buscarPorId(Long id) {
        if(repository.existsById(id)) {
            return true;
        }
        throw new HistoricoException("Histórico não encontrado.");
    }

    public HistoricoResponseDTO alterar(Long id, HistoricoRequestDTO dto) {
        Veiculo veiculo= veiculoRepository
                         .findById(dto.veiculoId())
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));
        
        Historico historico= repository
                             .findById(id)
                             .orElseThrow(()-> new HistoricoException("Histórico não encontrado."));

        historico.setId(id);
        historico.setDataManutencao(dto.dataManutencao());
        historico.setDescricao(dto.descricao());
        historico.setValorCobrado(dto.valorCobrado());
        historico.setVeiculo(veiculo);
        
        repository.save(historico);
        return new HistoricoResponseDTO(historico.getId(), dto.dataManutencao(), dto.descricao(), dto.valorCobrado(), veiculo.getId());
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}