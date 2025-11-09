package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.PortariaRequestDTO;
import br.com.fiap.Portaria.dto.PortariaResponseDTO;
import br.com.fiap.Portaria.service.PortariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portarias")
public class PortariaController {

    @Autowired
    private PortariaService portariaService;

    @GetMapping
    public List<PortariaResponseDTO> listarTodas() {
        return portariaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortariaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return portariaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody PortariaRequestDTO portariaRequestDTO) {
        portariaService.salvar(portariaRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody PortariaRequestDTO portariaRequestDTO) {
        try {
            portariaService.atualizar(id, portariaRequestDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            portariaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }
}