package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.config.MailConfig;
import br.com.serratec.projetoapi.dto.ClienteRequestDTO;
import br.com.serratec.projetoapi.dto.ClienteResponseDTO;
import br.com.serratec.projetoapi.dto.ViaCepResponseDTO;
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
    private MailConfig mailConfig;

    public Boolean buscar(Long id) {
        Boolean resultado = repository.existsById(id);
        return resultado;
    }

    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {
        if (dto.cep() == null || dto.cep().isBlank()) {
            throw new ClienteException("CEP não informado.");
        }
        ViaCepResponseDTO viaCep = viaCepService.buscarCep(dto.cep());
        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep());
        endereco.setLogradouro(viaCep.logradouro());
        endereco.setBairro(viaCep.bairro());
        endereco.setLocalidade(viaCep.localidade());
        endereco.setUf(viaCep.uf());

        Cliente salvo = new Cliente();
        salvo.setNome(dto.nome());
        salvo.setTelefone(dto.telefone());
        salvo.setCpf(dto.cpf());
        salvo.setEmail(dto.email());
        salvo.setCep(dto.cep());
        salvo.setEndereco(endereco);
        repository.save(salvo);

        mailConfig.sendEmail(
                salvo.getEmail(),
                "Cadastro realizado",
                "Cliente cadastrado com sucesso!");

        return new ClienteResponseDTO(salvo.getId(), salvo.getNome(), salvo.getTelefone(), salvo.getEmail());
    }

    public ClienteResponseDTO editar(Long id, ClienteRequestDTO dto) {
        Cliente editado = repository
                .findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado."));

        if (dto.cep() == null || dto.cep().isBlank()) {
            throw new ClienteException("CEP não informado.");
        }
        ViaCepResponseDTO viaCep = viaCepService.buscarCep(dto.cep());
        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep());
        endereco.setLogradouro(viaCep.logradouro());
        endereco.setBairro(viaCep.bairro());
        endereco.setLocalidade(viaCep.localidade());
        endereco.setUf(viaCep.uf());

        editado.setNome(dto.nome());
        editado.setTelefone(dto.telefone());
        editado.setCpf(dto.cpf());
        editado.setEmail(dto.email());
        editado.setCep(dto.cep());
        editado.setEndereco(endereco);
        repository.save(editado);

        mailConfig.sendEmail(
                editado.getEmail(),
                "Cadastro editado",
                "Cliente editado com sucesso!");

        return new ClienteResponseDTO(editado.getId(), editado.getNome(), editado.getTelefone(), editado.getEmail());
    }

    public Page<ClienteResponseDTO> listar(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getTelefone(),
                        cliente.getEmail()));
    }

}