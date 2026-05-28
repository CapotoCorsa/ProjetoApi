package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.model.Avaliacao;
import br.com.serratec.projetoapi.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    public Avaliacao criar(Avaliacao avaliacao) {
        avaliacao.setData(LocalDate.now());
        return repository.save(avaliacao);
    }

    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    public Avaliacao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }

    public Avaliacao atualizar(Long id, Avaliacao nova) {
        Avaliacao existente = buscarPorId(id);

        existente.setNota(nova.getNota());
        existente.setComentario(nova.getComentario());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
