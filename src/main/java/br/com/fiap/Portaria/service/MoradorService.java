package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.MoradorRequestDTO;
import br.com.fiap.Portaria.dto.MoradorResponseDTO;
import br.com.fiap.Portaria.entity.Apartamento;
import br.com.fiap.Portaria.entity.Morador;
import br.com.fiap.Portaria.repository.ApartamentoRepository;
import br.com.fiap.Portaria.repository.MoradorRepository;
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

    public List<MoradorResponseDTO> listarTodos() {
        return moradorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<MoradorResponseDTO> buscarPorId(Long id) {
        return moradorRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public MoradorResponseDTO salvar(MoradorRequestDTO moradorRequestDTO) {
        Morador morador = toEntity(moradorRequestDTO);
        Morador moradorSalvo = moradorRepository.save(morador);
        return toResponseDTO(moradorSalvo);
    }

    public MoradorResponseDTO atualizar(Long id, MoradorRequestDTO moradorRequestDTO) {
        return moradorRepository.findById(id)
                .map(morador -> {
                    morador.setNome(moradorRequestDTO.getNome());
                    morador.setBloco(moradorRequestDTO.getBloco());
                    morador.setContato(moradorRequestDTO.getContato());

                    if(moradorRequestDTO.getIdApartamento() != null) {
                        Apartamento apartamento = apartamentoRepository.findById(moradorRequestDTO.getIdApartamento())
                                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));
                        morador.setIdApartamento(apartamento);
                    }

                    Morador moradorSalvo = moradorRepository.save(morador);
                    return toResponseDTO(moradorSalvo);
                })
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));

    }

    public void deletar(Long id) {
        moradorRepository.deleteById(id);
    }

    private MoradorResponseDTO toResponseDTO(Morador morador) {
        return new MoradorResponseDTO(
                morador.getIdMorador(),
                morador.getNome(),
                morador.getContato(),
                morador.getBloco(),
                morador.getIdApartamento() != null ? morador.getIdApartamento().getIdApartamento() : null

        );
    }

    private Morador toEntity(MoradorRequestDTO dto) {
        Morador morador = new Morador();
        morador.setNome(dto.getNome());
        morador.setBloco(dto.getBloco());
        morador.setContato(dto.getContato());

        if(dto.getIdApartamento() != null){
            Apartamento apartamento = apartamentoRepository.findById(dto.getIdApartamento())
                    .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));
            morador.setIdApartamento(apartamento);
        }

        return morador;
    }
}
