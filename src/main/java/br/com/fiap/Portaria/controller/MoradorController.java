package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    public List<Morador> listarTodos() {
        return moradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Morador> buscarPorId(@PathVariable Long id) {
        return moradorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Morador criar(@RequestBody Morador morador) {
        return moradorService.salvar(morador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Morador> atualizar(@PathVariable Long id, @RequestBody Morador morador) {
        try {
            return ResponseEntity.ok(moradorService.atualizar(id, morador));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        moradorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
