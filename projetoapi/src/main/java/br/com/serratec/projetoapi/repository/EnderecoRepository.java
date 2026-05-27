package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);
}
