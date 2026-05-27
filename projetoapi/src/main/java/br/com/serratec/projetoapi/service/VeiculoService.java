package br.com.serratec.projetoapi.service;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.com.serratec.projetoapi.dto.VeiculoRequestDTO;
import br.com.serratec.projetoapi.dto.VeiculoResponseDTO;
import br.com.serratec.projetoapi.exception.ClienteException;
import br.com.serratec.projetoapi.exception.VeiculoException;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.ClienteRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;

public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<VeiculoResponseDTO> listar(Pageable pageable) {
        return repository
               .findAll(pageable)
               .map(veiculo-> new VeiculoResponseDTO (
                        veiculo.getId(), 
                        veiculo.getPlaca(), 
                        veiculo.getModelo(), 
                        veiculo.getCliente()
                    )
                );
    }

    public Boolean buscar(Long id) {
        Boolean resultado= repository.existsById(id);
        return resultado;
    }

    public VeiculoResponseDTO inserir(VeiculoRequestDTO dto) {
        Cliente cliente= clienteRepository
                         .findById(dto.idCliente())
                         .orElseThrow(()-> new ClienteException("Cliente não encontrado ou inválido."));

        Veiculo salvo= new Veiculo();
        salvo.setPlaca(dto.placa());
        salvo.setMarca(dto.marca());
        salvo.setModelo(dto.modelo());
        salvo.setAno(dto.ano());
        salvo.setCor(dto.cor());
        salvo.setCliente(clienteRepository.findById(dto.idCliente()).get());
        
        repository.save(salvo);
        return new VeiculoResponseDTO(salvo.getId(), salvo.getPlaca(), salvo.getModelo(), salvo.getCliente());
    }

    public VeiculoResponseDTO editar(Long id, VeiculoRequestDTO dto) {
        Cliente cliente= clienteRepository
                         .findById(dto.idCliente())
                         .orElseThrow(()-> new ClienteException("Cliente não encontrado ou inválido."));

        Veiculo editado= repository
                         .findById(id)
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado ou inválido."));

        editado.setPlaca(dto.placa());
        editado.setMarca(dto.marca());
        editado.setModelo(dto.modelo());
        editado.setAno(dto.ano());
        editado.setCor(dto.cor());
        editado.setCliente(clienteRepository.findById(dto.idCliente()).get());
        
        repository.save(editado);
        return new VeiculoResponseDTO(editado.getId(), editado.getPlaca(), editado.getModelo(), editado.getCliente());
    }
    
}
