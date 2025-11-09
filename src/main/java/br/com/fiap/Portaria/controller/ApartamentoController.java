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
    public ResponseEntity<ApartamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return apartamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApartamentoResponseDTO criar(@RequestBody ApartamentoRequestDTO apartamentoRequestDTO) {
        return apartamentoService.salvar(apartamentoRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> atualizar(@PathVariable Long id,@RequestBody ApartamentoRequestDTO apartamentoRequestDTO) {
        try{
            return ResponseEntity.ok(apartamentoService.atualizar(id, apartamentoRequestDTO));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> deletar(@PathVariable Long id) {
        apartamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}