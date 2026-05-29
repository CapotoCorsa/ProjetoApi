package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.AvaliacaoRequestDTO;
import br.com.serratec.projetoapi.dto.AvaliacaoResponseDTO;
import br.com.serratec.projetoapi.exception.ClienteException;
import br.com.serratec.projetoapi.model.Avaliacao;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.repository.AvaliacaoRepository;
import br.com.serratec.projetoapi.repository.ClienteRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao= new Avaliacao();

        Cliente cliente= clienteRepository
                         .findById(dto.clienteId())
                         .orElseThrow(()-> new ClienteException("Cliente não encontrado."));

        avaliacao.setCliente(cliente);
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setData(LocalDate.now());
        
        repository.save(avaliacao);
        return new AvaliacaoResponseDTO(avaliacao.getId(), dto.nota(), dto.comentario(), dto.data(), dto.clienteId());
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
