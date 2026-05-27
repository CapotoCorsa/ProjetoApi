package br.com.serratec.projetoapi.dto;

import br.com.serratec.projetoapi.model.Endereco;

public record ViaCepRequestDTO(String cep, String logradouro, String bairro, String localidade, String uf) {

    public ViaCepRequestDTO(Endereco endereco){
        this(
            endereco.getCep(),
            endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getLocalidade(),
            endereco.getUf());    
    }
}
