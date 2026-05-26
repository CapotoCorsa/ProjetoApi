package br.com.serratec.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.projetoapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.projetoapi.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-de-servico")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoResponseDTO inserir(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        return service.inserir(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrdemServicoResponseDTO> atualizar(@Valid @RequestBody OrdemServicoRequestDTO dto, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.atualizar(dto, id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<OrdemServicoResponseDTO> listar() {
        return service.listar();
    }
}
