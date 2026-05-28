package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Perfil;


public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
}
