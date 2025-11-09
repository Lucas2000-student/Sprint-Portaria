package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.MoradorRequestDTO;
import br.com.fiap.Portaria.dto.MoradorResponseDTO;
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
    public List<MoradorResponseDTO> listarTodos() {
        return moradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorResponseDTO> buscarPorId(@PathVariable Integer id) {
        return moradorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MoradorResponseDTO criar(@RequestBody MoradorRequestDTO moradorRequestDTO) {
        return moradorService.salvar(moradorRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoradorResponseDTO> atualizar(@PathVariable Integer id, @RequestBody MoradorRequestDTO moradorRequestDTO) {
        try {
            return ResponseEntity.ok(moradorService.atualizar(id, moradorRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        moradorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}