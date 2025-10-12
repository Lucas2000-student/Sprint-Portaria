package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.entity.Portaria;
import br.com.fiap.Portaria.repository.PortariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortariaService {

    @Autowired
    private PortariaRepository portariaRepository;

    public List<Portaria> listarTodas() {
        return portariaRepository.findAll();
    }

    public Optional<Portaria> buscarPorId(Long id) {
        return portariaRepository.findById(id);
    }

    public Portaria salvar(Portaria portaria) {
        return portariaRepository.save(portaria);
    }

    public Portaria atualizar(Long id, Portaria portariaAtualizada) {
        return portariaRepository.findById(id)
                .map(portaria -> {
                    portaria.setNomePorteiro(portariaAtualizada.getNomePorteiro());
                    portaria.setTurno(portariaAtualizada.getTurno());
                    portaria.setContato(portariaAtualizada.getContato());
                    portaria.setDataRegistro(portariaAtualizada.getDataRegistro());
                    return portariaRepository.save(portaria);
                })
                .orElseThrow(() -> new RuntimeException("Registro de portaria não encontrado"));
    }

    public void deletar(Long id) {
        portariaRepository.deleteById(id);
    }
}
