package br.com.serratec.projetoapi.controller;

import br.com.serratec.projetoapi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.dto.ClienteRequestDTO;
import br.com.serratec.projetoapi.dto.ClienteResponseDTO;
import br.com.serratec.projetoapi.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Inserir Cliente", description = "Insere um novo cliente.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Insere um novo cliente."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO inserir(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.inserir(dto);
    }

    @Operation(summary = "Editar Cliente", description = "Altera os valores de um cliente.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Altera os valores de um cliente."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> editar(
            @Valid @RequestBody ClienteRequestDTO dto,
            @PathVariable Long id) {

        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(id, dto));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar Clientes", description = "Lista todos os clientes cadastrados.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Lista todos os clientes cadastrados."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping
    public Page<ClienteResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }
    
}