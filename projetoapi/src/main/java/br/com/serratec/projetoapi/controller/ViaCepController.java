package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.projetoapi.dto.ViaCepResponseDTO;
import br.com.serratec.projetoapi.model.Cliente;
import br.com.serratec.projetoapi.service.ViaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class ViaCepController {

    @Autowired
    private ViaCepService service;

    @Operation(summary = "Buscar CEP", description = "Busca um CEP.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Busca um CEP."),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @GetMapping("{id}")
    public ResponseEntity<ViaCepResponseDTO> buscar(@PathVariable String cep){
        return ResponseEntity.ok(service.buscarCep(cep));
    }
}
