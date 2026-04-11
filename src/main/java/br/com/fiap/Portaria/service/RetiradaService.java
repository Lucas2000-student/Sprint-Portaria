package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.RetiradaRequestDTO;
import br.com.fiap.Portaria.dto.RetiradaResponseDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiradaService {

    @Autowired
    private RetiradaRepository retiradaRepository;

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private RetiradaProducer retiradaProducer;

    @Autowired
    private EntityManager entityManager;

    public RetiradaResponseDTO registrarRetirada(RetiradaRequestDTO dto) {
        // busca encomenda pelo token
        Encomenda encomenda = encomendaRepository.findByTokenEncomenda(dto.getEncomenda())
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada para o token: " + dto.getEncomenda()));

        // valida se já foi retirada
        if (Boolean.TRUE.equals(encomenda.getFoiRetirada())) {
            throw new RuntimeException("Encomenda já foi retirada");
        }

        // atualiza status da encomenda
        encomenda.setFoiRetirada(true);
        encomenda.setRetiradaEm(LocalDateTime.now());
        encomendaRepository.save(encomenda);

        // cria o registro de retirada
        Retirada retirada = new Retirada();
        retirada.setIdRetirada(buscarProximoIdRetirada());
        retirada.setDataRetirada(new java.util.Date());
        retirada.setTokenRetirada(dto.getEncomenda());
        retirada.setMorador(encomenda.getMorador());

        Retirada salva = retiradaRepository.save(retirada);

        retiradaProducer.notificarRetiradaRealizada(
                "Retirada realizada | MoradorID: " + salva.getMorador().getIdMorador() +
                        " | EncomendaID: " + encomenda.getIdEncomenda()
        );

        return toResponseDTO(salva, encomenda);
    }

    private Integer buscarProximoIdRetirada() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_RETIRADA), 0) + 1 FROM TPL_RETIRADA");
        return ((Number) query.getSingleResult()).intValue();
    }

    private RetiradaResponseDTO toResponseDTO(Retirada retirada, Encomenda encomenda) {
        return new RetiradaResponseDTO(
                retirada.getIdRetirada(),
                retirada.getDataRetirada(),
                retirada.getTokenRetirada(),
                retirada.getMorador() != null ? retirada.getMorador().getIdMorador() : null,
                encomenda.getIdEncomenda()
        );
    }
}