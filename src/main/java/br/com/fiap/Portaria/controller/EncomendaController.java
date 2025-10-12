package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.entity.Encomenda;
import br.com.fiap.Portaria.service.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encomendas")
public class EncomendaController {

    @Autowired
    private EncomendaService encomendaService;

    @GetMapping
    public List<Encomenda> listarTodas() {
        return encomendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encomenda> buscarPorId(@PathVariable Long id) {
        return encomendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Encomenda criar(@RequestBody Encomenda encomenda) {
        return encomendaService.salvar(encomenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encomenda> atualizar(@PathVariable Long id, @RequestBody Encomenda encomenda) {
        try {
            return ResponseEntity.ok(encomendaService.atualizar(id, encomenda));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        encomendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

