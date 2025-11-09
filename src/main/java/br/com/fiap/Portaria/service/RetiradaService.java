package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.RetiradaRequestDTO;
import br.com.fiap.Portaria.dto.RetiradaResponseDTO;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.entity.Portaria;
import br.com.fiap.Portaria.entity.Retirada;
import br.com.fiap.Portaria.repository.MoradorRepository;
import br.com.fiap.Portaria.repository.PortariaRepository;
import br.com.fiap.Portaria.repository.RetiradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetiradaService {

    @Autowired
    private RetiradaRepository retiradaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private PortariaRepository portariaRepository;

    public List<RetiradaResponseDTO> listarTodas() {
        return retiradaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<RetiradaResponseDTO> buscarPorId(Long id) {
        return retiradaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public RetiradaResponseDTO salvar(RetiradaRequestDTO retiradaRequestDTO) {
        Retirada retirada = toEntity(retiradaRequestDTO);
        Retirada retiradaSalva = retiradaRepository.save(retirada);
        return toResponseDTO(retiradaSalva);
    }

    public RetiradaResponseDTO atualizar(Long id, RetiradaRequestDTO retiradaRequestDTO) {
        return retiradaRepository.findById(id)
                .map(retirada -> {
                    retirada.setDataRetirada(retiradaRequestDTO.getDataRetirada());
                    retirada.setTokenRetirada(retiradaRequestDTO.getTokenRetirada());

                    if (retiradaRequestDTO.getMoradorId() != null) {
                        Morador morador = moradorRepository.findById(retiradaRequestDTO.getMoradorId())
                                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
                        retirada.setMorador(morador);
                    }

                    if (retiradaRequestDTO.getPortariaId() != null) {
                        Portaria portaria = portariaRepository.findById(retiradaRequestDTO.getPortariaId())
                                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
                        retirada.setPortaria(portaria);
                    }
                    Retirada retiradaSalva = retiradaRepository.save(retirada);
                    return toResponseDTO(retiradaSalva);
                })
                .orElseThrow(() -> new RuntimeException("Retirada não encontrada"));
    }

    public void deletar(Long id) {
        retiradaRepository.deleteById(id);
    }

    private RetiradaResponseDTO toResponseDTO(Retirada retirada) {
        return new RetiradaResponseDTO(
                retirada.getIdRetirada(),
                retirada.getDataRetirada(),
                retirada.getTokenRetirada(),
                retirada.getMorador() != null ? retirada.getMorador().getIdMorador() : null,
                retirada.getPortaria()!= null ? retirada.getPortaria().getIdPortaria() : null
        );
    }

    private Retirada toEntity(RetiradaRequestDTO dto) {
        Retirada retirada = new Retirada();
        retirada.setDataRetirada(dto.getDataRetirada());
        retirada.setTokenRetirada(dto.getTokenRetirada());

        if (dto.getMoradorId() != null) {
            Morador morador = moradorRepository.findById(dto.getMoradorId())
                    .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
            retirada.setMorador(morador);
        }

        if (dto.getPortariaId() != null) {
            Portaria portaria = portariaRepository.findById(dto.getPortariaId())
                    .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
            retirada.setPortaria(portaria);
        }

        return retirada;
    }

}
