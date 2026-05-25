package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

}
