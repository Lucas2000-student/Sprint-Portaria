package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.dto.ApartamentoRequestDTO;
import br.com.fiap.Portaria.dto.ApartamentoResponseDTO;
import br.com.fiap.Portaria.entity.Apartamento;
import br.com.fiap.Portaria.repository.ApartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartamentoService {

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    public List<ApartamentoResponseDTO> listarTodos(){
        return apartamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApartamentoResponseDTO> buscarPorId(Long id){
        return apartamentoRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public ApartamentoResponseDTO salvar(ApartamentoRequestDTO apartamentoRequestDTO) {
        Apartamento apartamento = toEntity(apartamentoRequestDTO);
        Apartamento salva = apartamentoRepository.save(apartamento);
        return toResponseDTO(salva);
    }

    public ApartamentoResponseDTO atualizar(Long id,ApartamentoRequestDTO apartamentoRequestDTO) {
        return apartamentoRepository.findById(id)
                .map(apartamento ->{
                    apartamento.setBloco(apartamentoRequestDTO.getBloco());
                    apartamento.setNumero(apartamentoRequestDTO.getNumero());
                    apartamento.setTorre(apartamentoRequestDTO.getTorre());

                    Apartamento apartamentoAtualizado = apartamentoRepository.save(apartamento);
                    return toResponseDTO(apartamentoAtualizado);
                })
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrada"));


    }

    public void deletar(Long id){
        apartamentoRepository.deleteById(id);
    }

    private ApartamentoResponseDTO toResponseDTO(Apartamento apartamento) {
        return new ApartamentoResponseDTO(
                apartamento.getIdApartamento(),
                apartamento.getTorre(),
                apartamento.getBloco(),
                apartamento.getNumero()
        );
    }

    private Apartamento toEntity(ApartamentoRequestDTO apartamentoRequestDTO) {
        Apartamento apartamento = new Apartamento();
        apartamento.setTorre(apartamentoRequestDTO.getTorre());
        apartamento.setBloco(apartamentoRequestDTO.getBloco());
        apartamento.setNumero(apartamentoRequestDTO.getNumero());

        return apartamento;
    }

}
