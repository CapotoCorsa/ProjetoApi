package br.com.serratec.projetoapi.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.model.Agendamento;
import br.com.serratec.projetoapi.service.AgendamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento inserir(@Valid @RequestBody Agendamento agendamento) {
        return service.agendar(agendamento);
    }

    @PutMapping("{id}")
    public ResponseEntity<Agendamento> editar(@Valid @RequestBody Agendamento agendamento, @PathVariable Long id) {
        if(service.buscar(id)) {
            if(agendamento.getStatus().name()== "CONCLUIDO") {
                agendamento.setId(id);
                return ResponseEntity.ok(service.concluir(agendamento));
            }
            agendamento.setId(id);
            return ResponseEntity.ok(service.cancelar(agendamento));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar(@RequestParam LocalDate data) {
        return ResponseEntity.ok(service.listarPorData(data));
    }

}
