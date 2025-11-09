package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.ApartamentoRequestDTO;
import br.com.fiap.Portaria.dto.ApartamentoResponseDTO;
import br.com.fiap.Portaria.entity.Apartamento;
import br.com.fiap.Portaria.repository.ApartamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartamentoService {

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ApartamentoResponseDTO> listarTodos() {
        return apartamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApartamentoResponseDTO> buscarPorId(Integer id) {
        return apartamentoRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public void salvar(ApartamentoRequestDTO apartamentoRequestDTO) {
        // 1. Busca próximo ID
        Integer proximoId = buscarProximoIdApartamento();

        // 2. Insere manualmente (SEM procedure)
        Apartamento apartamento = new Apartamento();
        apartamento.setIdApartamento(proximoId);
        apartamento.setTorre(apartamentoRequestDTO.getTorre());
        apartamento.setBloco(apartamentoRequestDTO.getBloco());
        apartamento.setNumero(apartamentoRequestDTO.getNumero());

        apartamentoRepository.save(apartamento); // ← JPA normal
    }

    private Integer buscarProximoIdApartamento() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_APARTAMENTO), 0) + 1 FROM TPL_APARTAMENTO");
        return ((Number) query.getSingleResult()).intValue();
    }

    public void atualizar(Integer id, ApartamentoRequestDTO apartamentoRequestDTO) {
        apartamentoRepository.atualizarApartamento(
                id,
                apartamentoRequestDTO.getTorre(),
                apartamentoRequestDTO.getBloco(),
                apartamentoRequestDTO.getNumero()
        );
    }

    public void deletar(Integer id) {
        if (!apartamentoRepository.existsById(id)) {
            throw new RuntimeException("Apartamento não encontrado");
        }
        apartamentoRepository.deletarApartamento(id);
    }

    private ApartamentoResponseDTO toResponseDTO(Apartamento apartamento) {
        return new ApartamentoResponseDTO(
                apartamento.getIdApartamento(),
                apartamento.getTorre(),
                apartamento.getBloco(),
                apartamento.getNumero()
        );
    }
}