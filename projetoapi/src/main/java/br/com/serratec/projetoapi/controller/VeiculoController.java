package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.serratec.projetoapi.dto.VeiculoRequestDTO;
import br.com.serratec.projetoapi.dto.VeiculoResponseDTO;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService service;

    @Operation(summary = "Listar Veiculos", description = "Lista todos os veiculos cadastrados.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Lista todos os veiculos cadastrados."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping
    public Page<VeiculoResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @Operation(summary = "Inserir Veículo", description = "Inseri um novo veículo.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Inseri um novo veículo."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PostMapping
    public VeiculoResponseDTO inserir(@Valid @RequestBody VeiculoRequestDTO dto) {
        return service.inserir(dto);
		
    }

    @Operation(summary = "Editar Veículo", description = "Edita os dados de um veículo.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Edita os dados de um veículo."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PutMapping("/{id}")
    public VeiculoResponseDTO editar(@Valid @PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.editar(id, dto);
    }

}