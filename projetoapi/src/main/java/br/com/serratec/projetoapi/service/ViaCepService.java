package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.projetoapi.dto.ViaCepResponseDTO;
import br.com.serratec.projetoapi.exception.ViaCepException;
import br.com.serratec.projetoapi.model.Endereco;
import br.com.serratec.projetoapi.repository.EnderecoRepository;

@Service
public class ViaCepService {
    @Autowired
    private EnderecoRepository repository;

    public ViaCepResponseDTO buscarCep(String cep) {
        Endereco enderecoBanco = repository.findByCep(cep);

        if (enderecoBanco != null) {
            return new ViaCepResponseDTO(enderecoBanco);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json";
            Endereco enderecoViaCep = restTemplate.getForObject(url, Endereco.class);
            if (enderecoViaCep != null) {
                enderecoViaCep.setCep(enderecoViaCep.getCep().replaceAll("-", ""));
                return inserir(enderecoViaCep);
            }
            else {
                throw new ViaCepException("Cep não encontrado!");
            }
        }
    }

    private ViaCepResponseDTO inserir(Endereco enderecoViaCep) {
        return new ViaCepResponseDTO(repository.save(enderecoViaCep));
    }
}
