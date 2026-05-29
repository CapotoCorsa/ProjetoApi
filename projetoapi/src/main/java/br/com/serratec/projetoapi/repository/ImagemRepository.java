//essa é a parte individual do Douglas Mathias Barboza

package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.model.Veiculo;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    List<Imagem> findByVeiculo(Veiculo Veiculo);

    

}
