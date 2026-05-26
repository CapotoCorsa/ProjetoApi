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

import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico inserir(@Valid @RequestBody OrdemServico servico) {
        return service.inserir(servico);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @RequestBody OrdemServico servico, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.atualizar(servico, id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return service.listar();
    }
}
