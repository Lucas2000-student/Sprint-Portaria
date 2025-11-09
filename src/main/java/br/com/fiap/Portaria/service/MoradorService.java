package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.MoradorRequestDTO;
import br.com.fiap.Portaria.dto.MoradorResponseDTO;
import br.com.fiap.Portaria.entity.Apartamento;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.repository.ApartamentoRepository;
import br.com.fiap.Portaria.repository.MoradorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private EntityManager entityManager;

    public List<MoradorResponseDTO> listarTodos() {
        return moradorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<MoradorResponseDTO> buscarPorId(Integer id) {
        return moradorRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public MoradorResponseDTO salvar(MoradorRequestDTO moradorRequestDTO) {
        Morador morador = toEntity(moradorRequestDTO);

        Integer proximoId = buscarProximoIdMorador();
        morador.setIdMorador(proximoId);

        Morador moradorSalvo = moradorRepository.save(morador);
        return toResponseDTO(moradorSalvo);
    }

    public MoradorResponseDTO atualizar(Integer id, MoradorRequestDTO moradorRequestDTO) {
        return moradorRepository.findById(id)
                .map(morador -> {
                    morador.setNome(moradorRequestDTO.getNome());
                    morador.setContato(moradorRequestDTO.getContato());

                    if(moradorRequestDTO.getApartamentoId() != null) {
                        Apartamento apartamento = apartamentoRepository.findById(moradorRequestDTO.getApartamentoId())
                                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));
                        morador.setApartamento(apartamento);
                    }

                    Morador moradorSalvo = moradorRepository.save(morador);
                    return toResponseDTO(moradorSalvo);
                })
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
    }

    public void deletar(Integer id) {
        moradorRepository.deleteById(id);
    }

    private Integer buscarProximoIdMorador() {
        Query query = entityManager.createNativeQuery("SELECT NVL(MAX(ID_MORADOR), 0) + 1 FROM TPL_MORADOR");
        return ((Number) query.getSingleResult()).intValue();
    }

    private MoradorResponseDTO toResponseDTO(Morador morador) {
        return new MoradorResponseDTO(
                morador.getIdMorador(),
                morador.getNome(),
                morador.getContato(),
                morador.getApartamento() != null ? morador.getApartamento().getIdApartamento() : null
        );
    }

    private Morador toEntity(MoradorRequestDTO dto) {
        Morador morador = new Morador();
        morador.setNome(dto.getNome());
        morador.setContato(dto.getContato());

        if(dto.getApartamentoId() != null){
            Apartamento apartamento = apartamentoRepository.findById(dto.getApartamentoId())
                    .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));
            morador.setApartamento(apartamento);
        }

        return morador;
    }
}