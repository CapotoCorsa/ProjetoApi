package br.com.serratec.projetoapi.controller;

import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserir(@Valid @RequestBody Cliente cliente) {
        return service.inserir(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> alterar(
            @Valid @RequestBody Cliente cliente,
            @PathVariable Long id) {

        if (service.buscarPorId(id) != null) {

            cliente.setId(id);

            return ResponseEntity.ok(service.inserir(cliente));
        }

        return ResponseEntity.notFound().build();
    }
}