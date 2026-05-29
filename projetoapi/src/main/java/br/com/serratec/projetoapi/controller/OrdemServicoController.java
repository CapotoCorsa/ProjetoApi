package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.projetoapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.projetoapi.model.OrdemServico;
import br.com.serratec.projetoapi.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-de-servico")
@Tag(name = "Ordens de Serviço", description = "Endpoints para gerenciamento das ordens de serviço")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService service;

    @Operation(summary = "Inserir Ordem de Serviço", description = "Insere uma nova ordem de serviço.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = OrdemServico.class), mediaType = "application/json")},
            description = "Insere uma nova ordem de serviço."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoResponseDTO inserir(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        return service.inserir(dto);
    }

    @Operation(summary = "Editar Ordem de Serviço", description = "Altera os valores de uma ordem de serviço.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = OrdemServico.class), mediaType = "application/json")},
            description = "Altera os valores de uma ordem de serviço."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping("{id}")
    public ResponseEntity<OrdemServicoResponseDTO> editar(@Valid @RequestBody OrdemServicoRequestDTO dto, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(dto, id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar Ordens de Serviço", description = "Lista todas as ordens de serviço cadastradas.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = OrdemServico.class), mediaType = "application/json")},
            description = "Lista todas as ordens de serviço cadastradas."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public Page<OrdemServicoResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }
}