package br.com.serratec.projetoapi.controller;

import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.service.ClienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.dto.ClienteRequestDTO;
import br.com.serratec.projetoapi.dto.ClienteResponseDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO inserir(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.inserir(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> editar(
            @Valid @RequestBody ClienteRequestDTO dto,
            @PathVariable Long id) {

        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(id, dto));
        }
        return ResponseEntity.notFound().build();
    }
}