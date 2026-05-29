package br.com.serratec.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.model.Avaliacao;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @Operation(summary = "Inserir Avaliação", description = "Insere uma avaliação.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Insere uma avaliação."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PostMapping
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return service.criar(avaliacao);
    }

    @Operation(summary = "Listar Avaliações", description = "Lista todas as avaliações.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Lista todas as avaliações."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @GetMapping
    public List<Avaliacao> listar() {
        return service.listar();
    }

    @Operation(summary = "Buscar Avaliação", description = "Busca uma avaliação.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Busca uma avaliação."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @GetMapping("/{id}")
    public Avaliacao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Alterar Avaliação", description = "Altera os dados de uma avaliação.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Altera os dados de uma avaliação."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PutMapping("/{id}")
    public Avaliacao atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        return service.atualizar(id, avaliacao);
    }

    @Operation(summary = "Deletar Avaliação", description = "Deleta os dados de uma avaliação.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Deleta os dados de uma avaliação."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}