package br.com.serratec.projetoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.projetoapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.projetoapi.exception.OrdemServicoException;
import br.com.serratec.projetoapi.exception.VeiculoException;
import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.repository.OrdemServicoRepository;
import br.com.serratec.projetoapi.repository.VeiculoRepository;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto) {
        Veiculo veiculo= veiculoRepository
                            .findById(dto.getIdVeiculo())
                            .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));
        
        OrdemServico salvo= new OrdemServico();
        salvo.setVeiculo(veiculo);
        salvo.setStatus(dto.getStatusOrdem());
        repository.save(salvo);

        return new OrdemServicoResponseDTO(salvo.getId(), salvo.getVeiculo().getId(), salvo.getStatus().name());
    }

    public OrdemServicoResponseDTO editar(OrdemServicoRequestDTO dto, Long id) {
        Veiculo veiculo= veiculoRepository
                         .findById(dto.getIdVeiculo())
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));
        
        OrdemServico editado= repository
                            .findById(id)
                            .orElseThrow(()-> new OrdemServicoException("Ordem de Serviço não encontrada."));
            
        editado.setVeiculo(veiculo);
        editado.setStatus(dto.getStatusOrdem());
        repository.save(editado);

        return new OrdemServicoResponseDTO(editado.getId(), editado.getVeiculo().getId(), editado.getStatus().name());
    }

    public Boolean buscar(Long id) {
        Boolean resultado= repository.existsById(id);
        return resultado;
    }

    public List<OrdemServicoResponseDTO> listar() {
        return repository
               .findAll()
               .stream()
               .map(ordem-> new OrdemServicoResponseDTO(ordem.getId(), ordem.getVeiculo().getId(), ordem.getStatus().name()))
               .collect(Collectors.toList());
    }
}
