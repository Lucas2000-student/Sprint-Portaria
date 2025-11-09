package br.com.fiap.Portaria.dto;

public class MoradorResponseDTO {

    private Long idMorador;
    private String nome;
    private Integer bloco;
    private String contato;
    private Long idApartamento;

    public MoradorResponseDTO() {
    }

    public MoradorResponseDTO(Long idMorador, String nome, String contato, Integer bloco, Long idApartamento) {
        this.idMorador = idMorador;
        this.nome = nome;
        this.contato = contato;
        this.bloco = bloco;
        this.idApartamento = idApartamento;
    }

    public Integer getBloco() {
        return bloco;
    }

    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long idMorador) {
        this.idMorador = idMorador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
