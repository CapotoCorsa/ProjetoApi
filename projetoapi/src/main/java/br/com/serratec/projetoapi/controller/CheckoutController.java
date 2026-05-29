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

import br.com.serratec.projetoapi.dto.CheckoutRequestDTO;
import br.com.serratec.projetoapi.dto.CheckoutResponseDTO;
import br.com.serratec.projetoapi.model.Checkout;
import br.com.serratec.projetoapi.service.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/checkouts")
@Tag(name = "Checkouts", description = "Endpoints para gerenciamento dos checkouts")
public class CheckoutController {
    @Autowired
    private CheckoutService service;

    @Operation(summary = "Inserir Checkout", description = "Insere um novo checkout.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Checkout.class), mediaType = "application/json")},
            description = "Insere um novo checkout."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CheckoutResponseDTO inserir(@Valid @RequestBody CheckoutRequestDTO dto) {
        return service.inserir(dto);
    }

    @Operation(summary = "Editar Checkout", description = "Altera os valores de um checkout.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Checkout.class), mediaType = "application/json")},
            description = "Altera os valores de um checkout."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PutMapping("{id}")
    public ResponseEntity<CheckoutResponseDTO> editar(@Valid @RequestBody CheckoutRequestDTO dto, @PathVariable Long id) {
        if(service.buscar(id)) {
            return ResponseEntity.ok(service.editar(dto, id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar Checkouts", description = "Lista todos os checkouts cadastrados.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Checkout.class), mediaType = "application/json")},
            description = "Lista todos os checkouts cadastrados."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public Page<CheckoutResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }
}