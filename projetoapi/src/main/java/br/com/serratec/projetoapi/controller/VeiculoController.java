package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.serratec.projetoapi.dto.VeiculoRequestDTO;
import br.com.serratec.projetoapi.dto.VeiculoResponseDTO;
import br.com.serratec.projetoapi.service.VeiculoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService service;

    @GetMapping
    public Page<VeiculoResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PostMapping
    public VeiculoResponseDTO inserir(@Valid @RequestBody VeiculoRequestDTO dto) {
        return service.inserir(dto);
    }

    @PutMapping("/{id}")
    public VeiculoResponseDTO editar(@Valid @PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.editar(id, dto);
    }

}