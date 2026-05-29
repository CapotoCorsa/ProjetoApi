package br.com.serratec.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.dto.AvaliacaoRequestDTO;
import br.com.serratec.projetoapi.dto.AvaliacaoResponseDTO;
import br.com.serratec.projetoapi.model.Avaliacao;
import br.com.serratec.projetoapi.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public AvaliacaoResponseDTO criar(@RequestBody AvaliacaoRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Avaliacao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}