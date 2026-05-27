package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.ViaCepDTO;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.model.Endereco;
import br.com.serratec.projetoapi.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private EmailService emailService;

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente inserir(Cliente cliente) {

        ViaCepDTO viaCep = viaCepService.buscarEndereco(cliente.getCep());

        Endereco endereco = new Endereco();
        endereco.setRua(viaCep.logradouro());
        endereco.setBairro(viaCep.bairro());
        endereco.setCidade(viaCep.localidade());
        endereco.setEstado(viaCep.uf());

        Cliente salvo = repository.save(cliente);

        emailService.enviarEmail(
                salvo.getEmail(),
                "Cadastro realizado",
                "Cliente cadastrado com sucesso!"
        );

        return salvo;
    }

public Cliente editar(Long id, Cliente cliente) {

        Cliente clienteBanco = repository.findById(id).orElse(null);

        if (clienteBanco == null) {
            return null;
        }

        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setTelefone(cliente.getTelefone());
        clienteBanco.setCpf(cliente.getCpf());
        clienteBanco.setEmail(cliente.getEmail());
        clienteBanco.setCep(cliente.getCep());

        ViaCepDTO viaCep = viaCepService.buscarEndereco(cliente.getCep());

        if (clienteBanco.getEndereco() == null) {
            clienteBanco.setEndereco(new Endereco());
        }

        clienteBanco.getEndereco().setRua(viaCep.logradouro());
        clienteBanco.getEndereco().setBairro(viaCep.bairro());
        clienteBanco.getEndereco().setCidade(viaCep.localidade());
        clienteBanco.getEndereco().setEstado(viaCep.uf());

        Cliente atualizado = repository.save(clienteBanco);

        emailService.enviarEmail(
                atualizado.getEmail(),
                "Cadastro atualizado",
                "Cliente atualizado com sucesso!"
        );

        return atualizado;
    }
}
