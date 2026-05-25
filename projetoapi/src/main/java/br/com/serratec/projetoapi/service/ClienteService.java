package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscarPorId(Long id) {
    return repository.findById(id).orElse(null);
}
    
    public Cliente inserir(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente editar(Long id, Cliente cliente) {

        Cliente clienteBanco = repository.findById(id).orElse(null);

        if (clienteBanco != null) {

            clienteBanco.setNome(cliente.getNome());
            clienteBanco.setTelefone(cliente.getTelefone());
            clienteBanco.setCpf(cliente.getCpf());
            clienteBanco.setEmail(cliente.getEmail());

            return repository.save(clienteBanco);
        }

        return null;
    }



}
