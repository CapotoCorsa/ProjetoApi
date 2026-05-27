package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.ClienteRequestDTO;
import br.com.serratec.projetoapi.dto.ClienteResponseDTO;
import br.com.serratec.projetoapi.dto.ViaCepDTO;
import br.com.serratec.projetoapi.exception.ClienteException;
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
        return repository
               .findById(id)
               .orElseThrow(()-> new ClienteException("Cliente não encontrado."));
    }

    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {
        ViaCepDTO viaCep = viaCepService.buscarEndereco(dto.cep());
        Endereco endereco = new Endereco();
        endereco.setRua(viaCep.logradouro());
        endereco.setBairro(viaCep.bairro());
        endereco.setCidade(viaCep.localidade());
        endereco.setEstado(viaCep.uf());

        Cliente salvo = new Cliente();
        salvo.setNome(dto.nome());
        salvo.setTelefone(dto.telefone());
        salvo.setCpf(dto.cpf());
        salvo.setEmail(dto.email());
        salvo.setCep(dto.cep());
        salvo.setEndereco(endereco);
        repository.save(salvo);

        emailService.enviarEmail(
                salvo.getEmail(),
                "Cadastro realizado",
                "Cliente cadastrado com sucesso!"
        );

        return new ClienteResponseDTO(salvo.getId(), salvo.getNome(), salvo.getTelefone(), salvo.getEmail());
    }

 public ClienteResponseDTO editar(Long id, ClienteRequestDTO dto) {
        Cliente editado = repository
                               .findById(id)
                               .orElseThrow(()-> new ClienteException("Cliente não encontrado."));

        ViaCepDTO viaCep = viaCepService.buscarEndereco(dto.cep());
        Endereco endereco = new Endereco();
        endereco.setRua(viaCep.logradouro());
        endereco.setBairro(viaCep.bairro());
        endereco.setCidade(viaCep.localidade());
        endereco.setEstado(viaCep.uf());
        
        editado.setNome(dto.nome());
        editado.setTelefone(dto.telefone());
        editado.setCpf(dto.cpf());
        editado.setEmail(dto.email());
        editado.setCep(dto.cep());
        editado.setEndereco(endereco);
        repository.save(editado);

        emailService.enviarEmail(
                editado.getEmail(),
                "Cadastro editado",
                "Cliente editado com sucesso!"
        );

        return new ClienteResponseDTO(editado.getId(), editado.getNome(), editado.getTelefone(), editado.getEmail());
    }
}
