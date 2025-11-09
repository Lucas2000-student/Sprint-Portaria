package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.PortariaRequestDTO;
import br.com.fiap.Portaria.dto.PortariaResponseDTO;
import br.com.fiap.Portaria.entity.Portaria;
import br.com.fiap.Portaria.repository.PortariaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortariaService {

    @Autowired
    private PortariaRepository portariaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<PortariaResponseDTO> listarTodas() {
        return portariaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PortariaResponseDTO> buscarPorId(Integer id) {
        return portariaRepository.findById(id)
                .map(this::toResponseDTO);
    }

    // SALVAR COM PROCEDURE + ID MANUAL
    public void salvar(PortariaRequestDTO portariaRequestDTO) {
        // 1. Busca próximo ID
        Integer proximoId = buscarProximoIdPortaria();

        // 2. Chama procedure passando o ID
        Query query = entityManager.createNativeQuery(
                "CALL INSERIR_PORTEIRO(:id, :nomePorteiro, :turno, :contato)"
        );
        query.setParameter("id", proximoId);
        query.setParameter("nomePorteiro", portariaRequestDTO.getNomePorteiro());
        query.setParameter("turno", portariaRequestDTO.getTurno());
        query.setParameter("contato", portariaRequestDTO.getContato());
        query.executeUpdate();
    }

    // ATUALIZAR COM PROCEDURE (já recebe ID)
    public void atualizar(Integer id, PortariaRequestDTO portariaRequestDTO) {
        portariaRepository.atualizarPorteiro(
                id,
                portariaRequestDTO.getTurno(),
                portariaRequestDTO.getContato()
        );
    }

    public void deletar(Integer id) {
        if (!portariaRepository.existsById(id)) {
            throw new RuntimeException("Portaria não encontrada");
        }
        portariaRepository.deletarPorteiro(id);
    }

    private Integer buscarProximoIdPortaria() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_PORTARIA), 0) + 1 FROM TPL_PORTARIA");
        return ((Number) query.getSingleResult()).intValue();
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
}