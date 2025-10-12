package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    public List<Morador> listarTodos() {
        return moradorRepository.findAll();
    }

    public Optional<Morador> buscarPorId(Long id) {
        return moradorRepository.findById(id);
    }

    public Morador salvar(Morador morador) {
        return moradorRepository.save(morador);
    }

    public Morador atualizar(Long id, Morador moradorAtualizado) {
        return moradorRepository.findById(id)
                .map(morador -> {
                    morador.setNome(moradorAtualizado.getNome());
                    morador.setApartamento(moradorAtualizado.getApartamento());
                    morador.setBloco(moradorAtualizado.getBloco());
                    morador.setContato(moradorAtualizado.getContato());
                    return moradorRepository.save(morador);
                })
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
    }

    public void deletar(Long id) {
        moradorRepository.deleteById(id);
    }
}
