package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.entity.Encomenda;
import br.com.fiap.Portaria.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public List<Encomenda> listarTodas() {
        return encomendaRepository.findAll();
    }

    public Optional<Encomenda> buscarPorId(Long id) {
        return encomendaRepository.findById(id);
    }

    public Encomenda salvar(Encomenda encomenda) {
        return encomendaRepository.save(encomenda);
    }

    public Encomenda atualizar(Long id, Encomenda encomendaAtualizada) {
        return encomendaRepository.findById(id)
                .map(encomenda -> {
                    encomenda.setDescricao(encomendaAtualizada.getDescricao());
                    encomenda.setDataRecebida(encomendaAtualizada.getDataRecebida());
                    encomenda.setStatus(encomendaAtualizada.getStatus());
                    encomenda.setMorador(encomendaAtualizada.getMorador());
                    encomenda.setRetirada(encomendaAtualizada.getRetirada());
                    return encomendaRepository.save(encomenda);
                })
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
    }

    public void deletar(Long id) {
        encomendaRepository.deleteById(id);
    }
}

