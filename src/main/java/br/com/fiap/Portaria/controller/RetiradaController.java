package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.RetiradaRequestDTO;
import br.com.fiap.Portaria.dto.RetiradaResponseDTO;
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
    public List<RetiradaResponseDTO> listarTodas() {
        return retiradaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetiradaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return retiradaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RetiradaResponseDTO criar(@RequestBody RetiradaRequestDTO retiradaRequestDTO) {
        return retiradaService.salvar(retiradaRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetiradaResponseDTO> atualizar(@PathVariable Integer id, @RequestBody RetiradaRequestDTO retiradaRequestDTO) {
        try {
            return ResponseEntity.ok(retiradaService.atualizar(id, retiradaRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        retiradaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
