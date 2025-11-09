package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.EncomendaRequestDTO;
import br.com.fiap.Portaria.dto.EncomendaResponseDTO;
import br.com.fiap.Portaria.entity.Encomenda;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.entity.Retirada;
import br.com.fiap.Portaria.repository.EncomendaRepository;
import br.com.fiap.Portaria.repository.MoradorRepository;
import br.com.fiap.Portaria.repository.RetiradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private RetiradaRepository retiradaRepository;

    public List<EncomendaResponseDTO> listarTodas() {
        return encomendaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EncomendaResponseDTO> buscarPorId(Long id) {
        return encomendaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public EncomendaResponseDTO salvar(EncomendaRequestDTO encomendaRequestDTO) {
        Encomenda encomenda = toEntity(encomendaRequestDTO);
        Encomenda encomendaSalva = encomendaRepository.save(encomenda);
        return toResponseDTO(encomendaSalva);
    }

    public EncomendaResponseDTO atualizar(Long id, EncomendaRequestDTO encomendaRequestDTO) {
        return encomendaRepository.findById(id)
                .map(encomenda -> {

                    encomenda.setDescricao(encomendaRequestDTO.getDescricao());
                    encomenda.setDataRecebida(encomendaRequestDTO.getDataRecebida());
                    encomenda.setStatus(encomendaRequestDTO.getStatus());

                    if (encomendaRequestDTO.getIdMorador() != null) {
                        Morador morador = moradorRepository.findById(encomendaRequestDTO.getIdMorador())
                                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
                        encomenda.setMorador(morador);
                    }

                    if (encomendaRequestDTO.getIdRetirada() != null) {
                        Retirada retirada = retiradaRepository.findById(encomendaRequestDTO.getIdRetirada())
                                .orElseThrow(()-> new RuntimeException("Requisisão de retirada não encontrada"));
                        encomenda.setRetirada(retirada);
                    }

                    Encomenda encomendaAtualizada = encomendaRepository.save(encomenda);
                    return toResponseDTO(encomendaAtualizada);
                })
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
    }

    public void deletar(Long id) {
        encomendaRepository.deleteById(id);
    }

    private EncomendaResponseDTO toResponseDTO(Encomenda encomenda) {
        return new EncomendaResponseDTO(
                encomenda.getIdEncomenda(),
                encomenda.getDescricao(),
                encomenda.getDataRecebida(),
                encomenda.getStatus(),
                encomenda.getMorador() != null ? encomenda.getMorador().getIdMorador() : null,
                encomenda.getRetirada() != null ? encomenda.getRetirada().getIdRetirada() : null
        );
    }

    private Encomenda toEntity(EncomendaRequestDTO dto) {
        Encomenda encomenda = new Encomenda();
        encomenda.setDescricao(dto.getDescricao());
        encomenda.setDataRecebida(dto.getDataRecebida());
        encomenda.setStatus(dto.getStatus());

        if (dto.getIdMorador() != null) {
            Morador morador = moradorRepository.findById(dto.getIdMorador())
                    .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
            encomenda.setMorador(morador);
        }

        if (dto.getIdRetirada() != null) {
            Retirada retirada = retiradaRepository.findById(dto.getIdRetirada())
                    .orElseThrow(() -> new RuntimeException("Solicitação de retirada não encontrada"));
            encomenda.setRetirada(retirada);
        }

        return encomenda;
    }
}