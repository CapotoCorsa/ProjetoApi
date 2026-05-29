package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    
}
