package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.model.Historico;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/historicos")
@Tag(name = "Historicos", description = "Endpoints para gerenciamento dos historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @Operation(summary = "Inserir Histórico", description = "Insere um histórico.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Insere um histórico."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PostMapping("/veiculo/{veiculoId}")
    public ResponseEntity<Historico> cadastrar(@PathVariable Long veiculoId, @RequestBody Historico historico) {
        Historico salvo = historicoService.salvar(veiculoId, historico);
        if (salvo != null) {
            return ResponseEntity.ok(salvo);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar Históricos", description = "Lista todos os históricos.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Lista todos os históricos."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<Page<Historico>> listar(@PathVariable Long veiculoId, Pageable pageable) {
        Page<Historico> lista = historicoService.buscarPorVeiculo(veiculoId, pageable);
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Alterar Histórico", description = "Altera os dados de um histórico.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Altera os dados de um histórico."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PutMapping("/{id}")
    public ResponseEntity<Historico> atualizar(@PathVariable Long id, @RequestBody Historico historico) {
        Historico atualizado = historicoService.alterar(id, historico);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar Histórico", description = "Deleta os dados de um histórico.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Deleta os dados de um histórico."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean deletado = historicoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}