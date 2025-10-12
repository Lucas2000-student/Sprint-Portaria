package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.entity.Retirada;
import br.com.fiap.Portaria.repository.RetiradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetiradaService {

    @Autowired
    private RetiradaRepository retiradaRepository;

    public List<Retirada> listarTodas() {
        return retiradaRepository.findAll();
    }

    public Optional<Retirada> buscarPorId(Long id) {
        return retiradaRepository.findById(id);
    }

    public Retirada salvar(Retirada retirada) {
        return retiradaRepository.save(retirada);
    }

    public Retirada atualizar(Long id, Retirada retiradaAtualizada) {
        return retiradaRepository.findById(id)
                .map(retirada -> {
                    retirada.setDataRetirada(retiradaAtualizada.getDataRetirada());
                    retirada.setTokenRetirada(retiradaAtualizada.getTokenRetirada());
                    retirada.setMorador(retiradaAtualizada.getMorador());
                    retirada.setPortaria(retiradaAtualizada.getPortaria());
                    return retiradaRepository.save(retirada);
                })
                .orElseThrow(() -> new RuntimeException("Retirada não encontrada"));
    }

    public void deletar(Long id) {
        retiradaRepository.deleteById(id);
    }
}
