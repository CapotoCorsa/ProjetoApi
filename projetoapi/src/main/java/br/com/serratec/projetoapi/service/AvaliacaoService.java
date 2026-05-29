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
    private OrdemServicoRepository ordemServicoRepository;

    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {

        OrdemServico ordemServico = ordemServicoRepository
                .findById(dto.ordemServicoId())
                .orElseThrow(() -> new OrdemServicoException("Ordem de serviço não encontrada."));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setOrdemServico(ordemServico);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setData(LocalDate.now());

        repository.save(avaliacao);

        return new AvaliacaoResponseDTO(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getData(),
                ordemServico.getId()
        );
    }

    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    public Avaliacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));
    }

    public void deletar(Long id) {
        repository.deleteById(id); 
    }
}