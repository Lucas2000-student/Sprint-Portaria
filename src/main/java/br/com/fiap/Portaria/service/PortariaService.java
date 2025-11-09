package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.PortariaRequestDTO;
import br.com.fiap.Portaria.dto.PortariaResponseDTO;
import br.com.fiap.Portaria.entity.Portaria;
import br.com.fiap.Portaria.repository.PortariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortariaService {

    @Autowired
    private PortariaRepository portariaRepository;

    public List<PortariaResponseDTO> listarTodas() {
        return portariaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PortariaResponseDTO> buscarPorId(Long id) {
        return portariaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public PortariaResponseDTO salvar(PortariaRequestDTO portariaRequestDTO) {
        Portaria portaria = toEntity(portariaRequestDTO);
        Portaria portariaSalva = portariaRepository.save(portaria);
        return toResponseDTO(portariaSalva);
    }

    public PortariaResponseDTO atualizar(Long id, PortariaRequestDTO portariaRequestDTO) {
        return portariaRepository.findById(id)
                .map(portaria -> {
                    portaria.setNomePorteiro(portariaRequestDTO.getNomePorteiro());
                    portaria.setTurno(portariaRequestDTO.getTurno());
                    portaria.setContato(portariaRequestDTO.getContato());
                    portaria.setDataRegistro(portariaRequestDTO.getDataRegistro());

                    Portaria portariaSalva = portariaRepository.save(portaria);
                    return toResponseDTO(portariaSalva);
                })
                .orElseThrow(() -> new RuntimeException("Registro de portaria não encontrado"));
    }

    public void deletar(Long id) {
        portariaRepository.deleteById(id);
    }

    private PortariaResponseDTO toResponseDTO(Portaria portaria) {
        return new PortariaResponseDTO(
                portaria.getIdPortaria(),
                portaria.getNomePorteiro(),
                portaria.getTurno(),
                portaria.getContato(),
                portaria.getDataRegistro()
        );
    }

    private Portaria toEntity(PortariaRequestDTO dto) {
        Portaria portaria = new Portaria();
        portaria.setNomePorteiro(dto.getNomePorteiro());
        portaria.setTurno(dto.getTurno());
        portaria.setContato(dto.getContato());
        portaria.setDataRegistro(dto.getDataRegistro());

        return portaria;
    }
}
