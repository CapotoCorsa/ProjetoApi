package br.com.serratec.projetoapi.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.projetoapi.dto.ViaCepDTO;
import br.com.serratec.projetoapi.exception.ViaCepException;

@Service
public class ViaCepService {

    public ViaCepDTO buscarEndereco(String cep) {

        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(conn.getInputStream(), ViaCepDTO.class);

        } catch (Exception e) {
            throw new ViaCepException("Erro ao buscar CEP. "+ e.getMessage());
        }
    }
}
