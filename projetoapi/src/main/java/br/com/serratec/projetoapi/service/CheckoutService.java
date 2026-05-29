package br.com.serratec.projetoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.dto.CheckoutRequestDTO;
import br.com.serratec.projetoapi.dto.CheckoutResponseDTO;
import br.com.serratec.projetoapi.exception.CheckoutException;
import br.com.serratec.projetoapi.exception.OrdemServicoException;
import br.com.serratec.projetoapi.exception.ServicoException;
import br.com.serratec.projetoapi.model.Checkout;
import br.com.serratec.projetoapi.model.Servico;
import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.repository.OrdemServicoRepository;
import br.com.serratec.projetoapi.repository.ServicoRepository;
import br.com.serratec.projetoapi.repository.CheckoutRepository;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository repository;

    @Autowired
    private ServicoRepository servicoRepository;
    
    @Autowired
    private OrdemServicoRepository ordemRepository;

    public CheckoutResponseDTO inserir(CheckoutRequestDTO dto) {
        Servico servico= servicoRepository
                         .findById(dto.servicoId())
                         .orElseThrow(()-> new ServicoException("Serviço não encontrado ou inválido."));
        OrdemServico ordem= ordemRepository
                         .findById(dto.ordemId())
                         .orElseThrow(()-> new OrdemServicoException("Ordem de Serviço não encontrada ou inválida."));

        Checkout salvo= new Checkout();
        salvo.setQuantidade(dto.quantidade());
        salvo.setDesconto(dto.desconto());
        salvo.setOrdem(ordem);
        salvo.setServico(servico);

        salvo.calcularSubtotal();
        repository.save(salvo);

        ordem.getCheckouts().add(salvo);
        ordem.calcularTotalGeral();
        ordemRepository.save(ordem);

        return new CheckoutResponseDTO(salvo.getId(), dto.ordemId(), dto.servicoId(), dto.quantidade(), dto.desconto()); 
    }

    public CheckoutResponseDTO editar(CheckoutRequestDTO dto, Long id) {
        Checkout editado= repository
                        .findById(id)
                        .orElseThrow(()-> new CheckoutException("Checkout não encontrado ou inválido."));
        Servico servico= servicoRepository
                         .findById(dto.servicoId())
                         .orElseThrow(()-> new ServicoException("Serviço não encontrado ou inválido."));
        OrdemServico ordem= ordemRepository
                         .findById(dto.ordemId())
                         .orElseThrow(()-> new OrdemServicoException("Ordem de Serviço não encontrada ou inválida."));

        editado.setId(id);
        editado.setQuantidade(dto.quantidade());
        editado.setDesconto(dto.desconto());
        editado.setOrdem(ordem);
        editado.setServico(servico);
        
        editado.calcularSubtotal();
        repository.save(editado);

        ordem.calcularTotalGeral();
        ordemRepository.save(ordem);

        return new CheckoutResponseDTO(editado.getId(), dto.ordemId(), dto.servicoId(), dto.quantidade(), dto.desconto());
    }

    public Boolean buscar(Long id) {
        Boolean resultado= repository.existsById(id);
        return resultado;
    }

    public Page<CheckoutResponseDTO> listar(Pageable pageable) {
        return repository
               .findAll(pageable)
               .map(checkout-> new CheckoutResponseDTO (
                        checkout.getId(), 
                        checkout.getOrdem().getId(), 
                        checkout.getServico().getId(),
                        checkout.getQuantidade(),
                        checkout.getDesconto()
                    )
                );
    }
}