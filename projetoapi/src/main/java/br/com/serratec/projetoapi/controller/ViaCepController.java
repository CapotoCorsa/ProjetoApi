package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.dto.ViaCepResponseDTO;
import br.com.serratec.projetoapi.service.ViaCepService;

@RestController
@RequestMapping("/enderecos")
public class ViaCepController {

    @Autowired
    private ViaCepService service;

    @GetMapping("{id}")
    public ResponseEntity<ViaCepResponseDTO> buscar(@PathVariable String cep){
        return ResponseEntity.ok(service.buscarCep(cep));
    }
}
