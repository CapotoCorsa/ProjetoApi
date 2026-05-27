package br.com.serratec.projetoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.model.Servico;
import br.com.serratec.projetoapi.repository.ServicoRepository;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository repository;

    public Servico inserir(Servico servico) {
        return repository.save(servico);
    }

    public Servico editar(Long id, Servico servico) {
        if(repository.existsById(id)) {
            servico.setId(id);
            return repository.save(servico);
        }
        return null;
    }

    public Boolean buscar(Long id) {
        Boolean resultado= repository.existsById(id);
        return resultado;
    }

    public List<Servico> listar() {
        return repository.findAll();
    }
}
