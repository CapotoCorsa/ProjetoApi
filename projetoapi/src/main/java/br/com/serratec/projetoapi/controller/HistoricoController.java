package br.com.serratec.projetoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.projetoapi.model.Historico;
import br.com.serratec.projetoapi.service.HistoricoService;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @PostMapping("/veiculo/{veiculoId}")
    public ResponseEntity<Historico> cadastrar(@PathVariable Long veiculoId, @RequestBody Historico historico) {
        Historico salvo = historicoService.salvar(veiculoId, historico);
        if (salvo != null) {
            return ResponseEntity.ok(salvo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<Page<Historico>> listar(@PathVariable Long veiculoId, Pageable pageable) {
        Page<Historico> lista = historicoService.buscarPorVeiculo(veiculoId, pageable);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historico> atualizar(@PathVariable Long id, @RequestBody Historico historico) {
        Historico atualizado = historicoService.alterar(id, historico);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean deletado = historicoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}