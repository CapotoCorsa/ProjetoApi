package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.AvaliacaoRequestDTO;
import br.com.serratec.projetoapi.dto.AvaliacaoResponseDTO;
import br.com.serratec.projetoapi.exception.OrdemServicoException;
import br.com.serratec.projetoapi.model.Avaliacao;
import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.repository.AvaliacaoRepository;
import br.com.serratec.projetoapi.repository.OrdemServicoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private OrdemServicoRepository ordemRepository;

    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao= new Avaliacao();

        OrdemServico ordem= ordemRepository
                         .findById(dto.ordemId())
                         .orElseThrow(()-> new OrdemServicoException("Ordem de Servico não encontrada."));

        avaliacao.setOrdem(ordem);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setData(LocalDate.now());
        
        repository.save(avaliacao);
        return new AvaliacaoResponseDTO(avaliacao.getId(), dto.nota(), dto.comentario(), dto.data(), dto.ordemId());
    }

    public List<AvaliacaoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(avaliacao-> new AvaliacaoResponseDTO(
                        avaliacao.getId(),
                        avaliacao.getNota(),
                        avaliacao.getComentario(),
                        avaliacao.getData(),
                        avaliacao.getOrdem().getId()
                ))
                .toList();
    }

    public AvaliacaoResponseDTO buscarPorId(Long id) {
        Avaliacao avaliacao= repository
                             .findById(id)
                             .orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));
        return new AvaliacaoResponseDTO(avaliacao.getId(), avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getData(), avaliacao.getOrdem().getId());
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
