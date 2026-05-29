package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.dto.HistoricoRequestDTO;
import br.com.serratec.projetoapi.dto.HistoricoResponseDTO;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/historicos")
@Tag(name = "Historicos", description = "Endpoints para gerenciamento dos historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoResponseDTO cadastrar(@Valid @RequestBody HistoricoRequestDTO dto) {
        return service.salvar(dto);
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
    @GetMapping("/veiculo/{id}")
    public Page<HistoricoResponseDTO> listar(@PathVariable Long id, Pageable pageable) {
        return service.buscarPorVeiculo(id, pageable);
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
    public ResponseEntity<HistoricoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody HistoricoRequestDTO dto) {
        if (service.buscarPorId(id)) {
            return ResponseEntity.ok(service.alterar(id, dto));
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
        boolean deletado = service.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}