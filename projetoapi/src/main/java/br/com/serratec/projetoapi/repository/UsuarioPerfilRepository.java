package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.UsuarioPerfil;
import br.com.serratec.projetoapi.model.UsuarioPerfilPK;


public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK>{

}
