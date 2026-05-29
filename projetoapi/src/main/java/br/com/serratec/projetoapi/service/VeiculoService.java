package br.com.serratec.projetoapi.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.com.serratec.projetoapi.dto.ClienteResponseDTO;
import br.com.serratec.projetoapi.dto.VeiculoRequestDTO;
import br.com.serratec.projetoapi.dto.VeiculoResponseDTO;
import br.com.serratec.projetoapi.exception.ClienteException;
import br.com.serratec.projetoapi.exception.VeiculoException;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.ClienteRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;
import jakarta.mail.Multipart;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ImagemService imagemService;

    //apenas fiz umas adaptações para colocar as imagens de um carro
    public Page<VeiculoResponseDTO> listar(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(veiculo -> adicionarUriImagem(veiculo));
    }

    // mesma situação do listar
    public VeiculoResponseDTO buscar(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new VeiculoException("Veículo não encontrado."));

        return adicionarUriImagem(veiculo);
    }

    // aqui eu tive que fazer algumas mudanças pra primeiro salvar o veiculo e dps
    // adicionar a foto
    public VeiculoResponseDTO inserir(VeiculoRequestDTO dto, MultipartFile file) throws Exception {
        Cliente cliente = clienteRepository
                .findById(dto.idCliente())
                .orElseThrow(() -> new ClienteException("Cliente não encontrado ou inválido."));

        Veiculo salvo = new Veiculo();
        salvo.setPlaca(dto.placa());
        salvo.setMarca(dto.marca());
        salvo.setModelo(dto.modelo());
        salvo.setAno(dto.ano());
        salvo.setCor(dto.cor());
        salvo.setCliente(cliente);

        salvo = repository.save(salvo);

        if (file != null) {
            imagemService.inserir(salvo, file);
        }

        return adicionarUriImagem(salvo);
    }

    public VeiculoResponseDTO editar(Long id, VeiculoRequestDTO dto) {
        Cliente cliente = clienteRepository
                .findById(dto.idCliente())
                .orElseThrow(() -> new ClienteException("Cliente não encontrado ou inválido."));

        Veiculo editado = repository
                .findById(id)
                .orElseThrow(() -> new VeiculoException("Veículo não encontrado ou inválido."));

        editado.setPlaca(dto.placa());
        editado.setMarca(dto.marca());
        editado.setModelo(dto.modelo());
        editado.setAno(dto.ano());
        editado.setCor(dto.cor());

        editado.setCliente(cliente);

        repository.save(editado);

        return adicionarUriImagem(editado);
    }

    // adiciona uma ou mais imagens para um veiculo ja existente
    public VeiculoResponseDTO adicionarUriImagem(Veiculo veiculo) {
        List<String> urls = java.util.Collections.emptyList();

        try {
            List<Imagem> imagens = imagemService.buscarPorVeiculo(veiculo);

            urls = imagens.stream().map(imagem -> {
                URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/imagens/{id}")
                        .buildAndExpand(imagem.getId()).toUri();
                return uri.toString();
            }).collect(Collectors.toList());

        } catch (Exception e) {
        }

        ClienteResponseDTO clienteDto = new ClienteResponseDTO(
                veiculo.getCliente().getId(),
                veiculo.getCliente().getNome(),
                veiculo.getCliente().getTelefone(),
                veiculo.getCliente().getEmail());

        return new VeiculoResponseDTO(veiculo.getId(), veiculo.getPlaca(), veiculo.getModelo(), clienteDto, urls);
    }

}