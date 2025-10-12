package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.entity.Retirada;
import br.com.fiap.Portaria.service.RetiradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retiradas")
public class RetiradaController {

    @Autowired
    private RetiradaService retiradaService;

    @GetMapping
    public List<Retirada> listarTodas() {
        return retiradaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retirada> buscarPorId(@PathVariable Long id) {
        return retiradaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Retirada criar(@RequestBody Retirada retirada) {
        return retiradaService.salvar(retirada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Retirada> atualizar(@PathVariable Long id, @RequestBody Retirada retirada) {
        try {
            return ResponseEntity.ok(retiradaService.atualizar(id, retirada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        retiradaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
