package br.com.serratec.projetoapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.model.Historico;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.HistoricoRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Historico salvar(Long veiculoId, Historico historico) {
        Optional<Veiculo> veiculoNoBanco = veiculoRepository.findById(veiculoId);
        
        if (veiculoNoBanco.isPresent()) {
            historico.setVeiculo(veiculoNoBanco.get());
            return historicoRepository.save(historico);
        } else {
            return null;
        }
    }

    public Page<Historico> buscarPorVeiculo(Long veiculoId, Pageable pageable) {
        return historicoRepository.findByVeiculoId(veiculoId, pageable);
    }

    public Historico alterar(Long id, Historico novosDados) {
        Optional<Historico> historicoNoBanco = historicoRepository.findById(id);
        
        if (historicoNoBanco.isPresent()) {
            Historico historicoAtual = historicoNoBanco.get();
            historicoAtual.setDescricao(novosDados.getDescricao());
            historicoAtual.setValorCobrado(novosDados.getValorCobrado());
            historicoAtual.setDataManutencao(novosDados.getDataManutencao());
            return historicoRepository.save(historicoAtual);
        } else {
            return null;
        }
    }

    public boolean deletar(Long id) {
        if (historicoRepository.existsById(id)) {
            historicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}