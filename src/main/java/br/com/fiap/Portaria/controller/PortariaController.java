package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.PortariaRequestDTO;
import br.com.fiap.Portaria.dto.PortariaResponseDTO;
import br.com.fiap.Portaria.entity.Portaria;
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
    public ResponseEntity<PortariaResponseDTO> buscarPorId(@PathVariable Long id) {
        return portariaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PortariaResponseDTO criar(@RequestBody PortariaRequestDTO portariaRequestDTO) {
        return portariaService.salvar(portariaRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PortariaResponseDTO> atualizar(@PathVariable Long id, @RequestBody PortariaRequestDTO portariaRequestDTO) {
        try {
            return ResponseEntity.ok(portariaService.atualizar(id, portariaRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        portariaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
