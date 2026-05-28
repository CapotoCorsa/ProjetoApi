package br.com.serratec.projetoapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.projetoapi.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    
    Page<Historico> findByVeiculoId(Long veiculoId, Pageable pageable);
}