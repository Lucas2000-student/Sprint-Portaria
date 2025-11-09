package br.com.fiap.Portaria.dto;

public class MoradorRequestDTO {
    private String nome;
    private String contato;
    private Integer apartamentoId;

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