package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.EncomendaRequestDTO;
import br.com.fiap.Portaria.dto.EncomendaResponseDTO;
import br.com.fiap.Portaria.entity.Encomenda;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.repository.EncomendaRepository;
import br.com.fiap.Portaria.repository.MoradorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private EntityManager entityManager;

    @Autowired
    private EncomendaProducer encomendaProducer;

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

    public EncomendaResponseDTO buscarPorToken(String token) {
        Encomenda encomenda = encomendaRepository.findByTokenEncomenda(token)
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada para o token: " + token));
        return toResponseDTO(encomenda);
    }

    public EncomendaResponseDTO salvar(EncomendaRequestDTO dto) {
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição da encomenda é obrigatória");
        }
        if (dto.getMoradorId() == null) {
            throw new RuntimeException("Morador é obrigatório para registrar encomenda");
        }

        Encomenda encomenda = new Encomenda();
        encomenda.setDescricao(dto.getDescricao());
        encomenda.setOrigem(dto.getOrigem());
        encomenda.setFoiRetirada(false);
        encomenda.setTokenEncomenda(gerarToken());
        encomenda.setDataRecebida(new Date());

        Morador morador = moradorRepository.findById(dto.getMoradorId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        encomenda.setMorador(morador);

        Integer proximoId = buscarProximoIdEncomenda();
        encomenda.setIdEncomenda(proximoId);

        Encomenda salva = encomendaRepository.save(encomenda);
        encomendaProducer.notificarEncomendaRecebida(
                "Nova encomenda recebida: " + salva.getDescricao() +
                        " | Morador ID: " + salva.getMorador().getIdMorador()
        );
        return toResponseDTO(salva);
    }

    public EncomendaResponseDTO atualizar(Integer id, EncomendaRequestDTO dto) {
        return encomendaRepository.findById(id)
                .map(encomenda -> {
                    if (dto.getDescricao() != null) encomenda.setDescricao(dto.getDescricao());
                    if (dto.getOrigem() != null) encomenda.setOrigem(dto.getOrigem());

                    if (dto.getMoradorId() != null) {
                        Morador morador = moradorRepository.findById(dto.getMoradorId())
                                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
                        encomenda.setMorador(morador);
                    }

                    return toResponseDTO(encomendaRepository.save(encomenda));
                })
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
    }

    public void deletar(Integer id) {
        if (!encomendaRepository.existsById(id)) {
            throw new RuntimeException("Encomenda não encontrada");
        }
        encomendaRepository.deleteById(id);
    }

    private Integer buscarProximoIdEncomenda() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_ENCOMENDA), 0) + 1 FROM TPL_ENCOMENDA");
        return ((Number) query.getSingleResult()).intValue();
    }

    private String gerarToken() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 5; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }
        return token.toString();
    }

    private EncomendaResponseDTO toResponseDTO(Encomenda encomenda) {
        EncomendaResponseDTO.MoradorResumoDTO moradorResumo = null;
        if (encomenda.getMorador() != null) {
            moradorResumo = new EncomendaResponseDTO.MoradorResumoDTO(
                    encomenda.getMorador().getIdMorador(),
                    encomenda.getMorador().getNome()
            );
        }
        return new EncomendaResponseDTO(
                encomenda.getIdEncomenda(),
                encomenda.getTokenEncomenda(),
                encomenda.getOrigem(),
                encomenda.getDescricao(),
                encomenda.getFoiRetirada(),
                moradorResumo
        );
    }
}