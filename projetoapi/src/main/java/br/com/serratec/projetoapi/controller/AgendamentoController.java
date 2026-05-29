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

import br.com.serratec.projetoapi.dto.AgendamentoRequestDTO;
import br.com.serratec.projetoapi.dto.AgendamentoResponseDTO;
import br.com.serratec.projetoapi.model.Agendamento;
import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento dos agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @Operation(summary = "Inserir agendamento", description = "Insere um agendamento.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Insere um agendamento."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponseDTO inserir(@Valid @RequestBody AgendamentoRequestDTO dto) {
        return service.agendar(dto);
    }

    @Operation(summary = "Cancelar agendamento", description = "Cancela um agendamento.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Cancela um agendamento."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<AgendamentoResponseDTO> cancelar(@PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.cancelar(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Concluir agendamento", description = "Conclui um agendamento.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Conclui um agendamento."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PutMapping("/{id}/concluir")
    public ResponseEntity<AgendamentoResponseDTO> editar(@PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.concluir(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar agendamentos", description = "Lista todos os agendamentos.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Lista todos os agendamentos."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar(@RequestParam LocalDate data) {
        return ResponseEntity.ok(service.listarPorData(data));
    }

}
