package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.EncomendaRequestDTO;
import br.com.fiap.Portaria.dto.EncomendaResponseDTO;
import br.com.fiap.Portaria.entity.Encomenda;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.entity.Retirada;
import br.com.fiap.Portaria.repository.EncomendaRepository;
import br.com.fiap.Portaria.repository.MoradorRepository;
import br.com.fiap.Portaria.repository.RetiradaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    @Autowired
    private EntityManager entityManager;

    public List<EncomendaResponseDTO> listarTodas() {
        return encomendaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EncomendaResponseDTO> buscarPorId(Integer id) {
        return encomendaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public EncomendaResponseDTO salvar(EncomendaRequestDTO encomendaRequestDTO) {
        Encomenda encomenda = toEntity(encomendaRequestDTO);

        Integer proximoId = buscarProximoIdEncomenda();
        encomenda.setIdEncomenda(proximoId);

        Encomenda encomendaSalva = encomendaRepository.save(encomenda);
        return toResponseDTO(encomendaSalva);
    }

    public EncomendaResponseDTO atualizar(Integer id, EncomendaRequestDTO encomendaRequestDTO) {
        return encomendaRepository.findById(id)
                .map(encomenda -> {
                    encomenda.setDescricao(encomendaRequestDTO.getDescricao());
                    encomenda.setDataRecebida(encomendaRequestDTO.getDataRecebida());
                    encomenda.setStatus(encomendaRequestDTO.getStatus());

                    if (encomendaRequestDTO.getMoradorId() != null) {
                        Morador morador = moradorRepository.findById(encomendaRequestDTO.getMoradorId())
                                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
                        encomenda.setMorador(morador);
                    }

                    if (encomendaRequestDTO.getRetiradaId() != null) {
                        Retirada retirada = retiradaRepository.findById(encomendaRequestDTO.getRetiradaId())
                                .orElseThrow(()-> new RuntimeException("Requisição de retirada não encontrada"));
                        encomenda.setRetirada(retirada);
                    }

                    Encomenda encomendaAtualizada = encomendaRepository.save(encomenda);
                    return toResponseDTO(encomendaAtualizada);
                })
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
    }

    public void deletar(Integer id) {
        encomendaRepository.deleteById(id);
    }

    private Integer buscarProximoIdEncomenda() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_ENCOMENDA), 0) + 1 FROM TPL_ENCOMENDA");
        return ((Number) query.getSingleResult()).intValue();
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

        if (dto.getMoradorId() != null) {
            Morador morador = moradorRepository.findById(dto.getMoradorId())
                    .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
            encomenda.setMorador(morador);
        }

        if (dto.getRetiradaId() != null) {
            Retirada retirada = retiradaRepository.findById(dto.getRetiradaId())
                    .orElseThrow(() -> new RuntimeException("Solicitação de retirada não encontrada"));
            encomenda.setRetirada(retirada);
        }

        return encomenda;
    }
}