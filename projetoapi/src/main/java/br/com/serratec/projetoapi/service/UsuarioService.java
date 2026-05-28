package br.com.serratec.projetoapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.config.MailConfig;
import br.com.serratec.projetoapi.dto.UsuarioRequestDTO;
import br.com.serratec.projetoapi.dto.UsuarioResponseDTO;
import br.com.serratec.projetoapi.exception.UsuarioException;
import br.com.serratec.projetoapi.model.Usuario;
import br.com.serratec.projetoapi.model.UsuarioPerfil;
import br.com.serratec.projetoapi.repository.UsuarioPerfilRepository;
import br.com.serratec.projetoapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;
    
    @Autowired
    private BCryptPasswordEncoder criptografar;

    @Autowired
    private MailConfig config;

    UsuarioService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll().stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO dto){
        Usuario usuarioBanco = repository.findByEmail(dto.getEmail());
        if (usuarioBanco != null) {
            throw new UsuarioException("E-mail já cadastrado.");    
        }
        Usuario usuario= new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(criptografar.encode(dto.getSenha()));        
        Usuario usuarioSalvo= repository.save(usuario);

        for(UsuarioPerfil up: dto.getUsuarioPerfis()) {
            up.setUsuario(usuarioSalvo);
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()).get());
            up.setDataCriacao(LocalDate.now());
            up.setAtivo(true);
        }
        usuarioPerfilRepository.saveAll(dto.getUsuarioPerfis());

        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }

}