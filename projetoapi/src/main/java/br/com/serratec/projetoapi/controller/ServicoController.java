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
import io.swagger.v3.oas.annotations.Operation; // Alteração: Import do Swagger
import io.swagger.v3.oas.annotations.media.Content; // Alteração: Import do Swagger
import io.swagger.v3.oas.annotations.media.Schema; // Alteração: Import do Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Alteração: Import do Swagger
import io.swagger.v3.oas.annotations.responses.ApiResponses; // Alteração: Import do Swagger
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Endpoints para gerenciamento dos serviços")
public class ServicoController {
    @Autowired
    private ServicoService service;

    @Operation(summary = "Inserir Serviço", description = "Insere um novo serviço.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Servico.class), mediaType = "application/json")},
            description = "Insere um novo serviço."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico inserir(@Valid @RequestBody Servico servico) {
        return service.inserir(servico);
    }

    @Operation(summary = "Editar Serviço", description = "Altera os valores de um serviço.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Servico.class), mediaType = "application/json")},
            description = "Altera os valores de um serviço."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping("{id}")
    public ResponseEntity<Servico> atualizar(@Valid @RequestBody Servico servico, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(id, servico));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar Serviços", description = "Lista todos os serviços cadastrados.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Servico.class), mediaType = "application/json")},
            description = "Lista todos os serviços cadastrados."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public List<Servico> listar() {
        return service.listar();
    }
}