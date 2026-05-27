package br.com.serratec.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.model.Servico;
import br.com.serratec.projetoapi.service.ServicoService;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico inserir(@Valid @RequestBody Servico servico) {
        return service.inserir(servico);
    }

    @PutMapping("{id}")
    public ResponseEntity<Servico> atualizar(@Valid @RequestBody Servico servico, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(id, servico));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Servico> listar() {
        return service.listar();
    }
}
