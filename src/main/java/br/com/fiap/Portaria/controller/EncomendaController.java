package br.com.fiap.Portaria.controller;

import br.com.fiap.Portaria.dto.EncomendaRequestDTO;
import br.com.fiap.Portaria.dto.EncomendaResponseDTO;
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
    public List<EncomendaResponseDTO> listarTodas() {
        return encomendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncomendaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return encomendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EncomendaResponseDTO criar(@RequestBody EncomendaRequestDTO encomendaRequestDTO) {
        return encomendaService.salvar(encomendaRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncomendaResponseDTO> atualizar(@PathVariable Integer id, @RequestBody EncomendaRequestDTO encomendaRequestDTO) {
        try {
            return ResponseEntity.ok(encomendaService.atualizar(id, encomendaRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        encomendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}