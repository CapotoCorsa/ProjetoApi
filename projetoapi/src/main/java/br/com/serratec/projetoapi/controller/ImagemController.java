package br.com.serratec.projetoapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.projetoapi.model.Imagem;
import br.com.serratec.projetoapi.model.Veiculo;
import br.com.serratec.projetoapi.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/imagens")
@Tag(name = "Imagens", description = "Endpoints para gerenciamento das fotos dos veículos")
public class ImagemController {

    @Autowired
    private ImagemService service;

    @Operation(summary = "Inserir Imagem", description = "Efetua o upload de uma imagem para um veículo.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Imagem inserida com sucesso."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PostMapping(value = "/veiculo/{idVeiculo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Imagem> inserir(@PathVariable Long idVeiculo, @RequestParam MultipartFile file) throws IOException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(idVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(veiculo, file));
    }

    @Operation(summary = "Buscar Imagem", description = "Retorna a foto do veículo para ser exibida no navegador.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Imagem localizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada") 
    })
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> buscar(@PathVariable Long id) {
        Imagem imagem = service.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, imagem.getTipo()); // Define se é JPG, PNG, etc.
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(imagem.getDados().length));
        
        return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Editar Imagem", description = "Substitui uma imagem existente por uma nova.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", 
            content = {@Content(schema = @Schema(implementation = Imagem.class), mediaType = "application/json")},
            description = "Imagem alterada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada") 
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Imagem> alterar(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.alterar(id, file));
    }

    @Operation(summary = "Deletar Imagem", description = "Remove a imagem do banco de dados.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "Imagem deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Imagem não encontrada") 
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}