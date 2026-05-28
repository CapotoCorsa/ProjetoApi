//essa é a parte individual do Douglas Mathias Barboza

package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.model.Veiculo;

import java.util.Optional;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    Optional<Imagem> findByVeiculo(Veiculo Veiculo);

    

}
