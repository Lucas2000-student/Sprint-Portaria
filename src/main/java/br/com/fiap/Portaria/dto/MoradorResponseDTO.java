package br.com.fiap.Portaria.dto;

public class MoradorResponseDTO {
    private Integer idMorador;
    private String nome;
    private String contato;
    private Integer apartamentoId;

    public MoradorResponseDTO() {}

    public MoradorResponseDTO(Integer idMorador, String nome, String contato, Integer apartamentoId) {
        this.idMorador = idMorador;
        this.nome = nome;
        this.contato = contato;
        this.apartamentoId = apartamentoId;
    }

    public Integer getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Integer idMorador) {
        this.idMorador = idMorador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Integer getApartamentoId() {
        return apartamentoId;
    }

    public void setApartamentoId(Integer apartamentoId) {
        this.apartamentoId = apartamentoId;
    }
}
