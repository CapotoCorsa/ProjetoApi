package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.projetoapi.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}