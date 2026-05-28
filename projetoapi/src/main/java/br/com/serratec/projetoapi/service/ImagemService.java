package br.com.serratec.projetoapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.projetoapi.exception.ImagemException;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.ImagemRepository;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository repository;

    public Imagem inserir(Veiculo veiculo, MultipartFile file) throws IOException {
        Imagem imagem = new Imagem(null, file.getBytes(), file.getContentType(), file.getName(), veiculo);
        return repository.save(imagem);
    }

    public Imagem buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ImagemException("Imagem não encontrada com o ID: " + id));
    }

    public Imagem buscarPorVeiculo(Veiculo veiculo) {
        return repository.findByVeiculo(veiculo)
                .orElseThrow(() -> new ImagemException("Nenhuma imagem encontrada para o veículo informado."));
    }

    public Imagem alterar(Long id, MultipartFile file) throws IOException {
        Imagem imagemExistente = repository.findById(id)
                .orElseThrow(() -> new ImagemException("Imagem não encontrada com o ID: " + id));

        imagemExistente.setDados(file.getBytes());
        imagemExistente.setTipo(file.getContentType());
        imagemExistente.setNome(file.getName());

        return repository.save(imagemExistente);
    }

    public void deletar(Long id) {
        Imagem imagem = repository.findById(id)
                .orElseThrow(() -> new ImagemException("Imagem não encontrada com o ID: " + id));

        repository.delete(imagem);
    }
}