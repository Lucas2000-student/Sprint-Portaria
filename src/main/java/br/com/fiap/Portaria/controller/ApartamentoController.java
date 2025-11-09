package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.ApartamentoRequestDTO;
import br.com.fiap.Portaria.dto.ApartamentoResponseDTO;
import br.com.fiap.Portaria.service.ApartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController {

    @Autowired
    private ApartamentoService apartamentoService;

    @GetMapping
    public List<ApartamentoResponseDTO> listar() {
        return apartamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return apartamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody ApartamentoRequestDTO apartamentoRequestDTO) {
        apartamentoService.salvar(apartamentoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody ApartamentoRequestDTO apartamentoRequestDTO) {
        try {
            apartamentoService.atualizar(id, apartamentoRequestDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            apartamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}