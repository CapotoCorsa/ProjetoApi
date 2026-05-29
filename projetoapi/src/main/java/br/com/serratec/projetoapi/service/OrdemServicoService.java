package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.projetoapi.enums.StatusOrdem;
import br.com.serratec.projetoapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.projetoapi.exception.EnumException;
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

    @Autowired
    private NotificacaoService notificacaoService;

    public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto) {
        Veiculo veiculo= veiculoRepository
                            .findById(dto.idVeiculo())
                            .orElseThrow(()-> new VeiculoException("Veículo não encontrado."));
        
        OrdemServico salvo= new OrdemServico();
        salvo.setVeiculo(veiculo);
        salvo.setStatus(setStatus(dto.statusOrdem()));
        salvo.calcularTotalGeral();
        repository.save(salvo);

        return new OrdemServicoResponseDTO(salvo.getId(), salvo.getVeiculo().getId(), salvo.getStatus().name(), salvo.getTotalGeral());
    }

    public OrdemServicoResponseDTO editar(OrdemServicoRequestDTO dto, Long id) {
        Veiculo veiculo= veiculoRepository
                         .findById(dto.idVeiculo())
                         .orElseThrow(()-> new VeiculoException("Veículo não encontrado ou inválido."));
        
        OrdemServico editado= repository
                            .findById(id)
                            .orElseThrow(()-> new OrdemServicoException("Ordem de Serviço não encontrada ou inválida."));
            
        editado.setVeiculo(veiculo);
        editado.setStatus(setStatus(dto.statusOrdem()));
        editado.calcularTotalGeral();
        repository.save(editado);

        notificacaoService.notificar(editado.getVeiculo().getCliente().getEmail(), editado.getStatus().name());
        return new OrdemServicoResponseDTO(editado.getId(), editado.getVeiculo().getId(), editado.getStatus().name(), editado.getTotalGeral());
    }

    public Boolean buscar(Long id) {
        Boolean resultado= repository.existsById(id);
        return resultado;
    }

    public Page<OrdemServicoResponseDTO> listar(Pageable pageable) {
        return repository
               .findAll(pageable)
               .map(ordem-> new OrdemServicoResponseDTO (
                        ordem.getId(), 
                        ordem.getVeiculo().getId(), 
                        ordem.getStatus().name(),
                        ordem.getTotalGeral()
                    )
                );
    }

    private StatusOrdem setStatus(String nome) {
        for(StatusOrdem status: StatusOrdem.values()) {
            if(status.name().equals(nome))
                return status;
        }
        throw new EnumException("Status inválido.");
    };

}