package br.com.serratec.projetoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository repository;

    public OrdemServico inserir(OrdemServico servico) {
        return repository.save(servico);
    }

    public OrdemServico atualizar(OrdemServico servico, Long id) {
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

    public List<OrdemServico> listar() {
        return repository.findAll();
    }
}
