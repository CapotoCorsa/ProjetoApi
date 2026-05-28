package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.projetoapi.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
